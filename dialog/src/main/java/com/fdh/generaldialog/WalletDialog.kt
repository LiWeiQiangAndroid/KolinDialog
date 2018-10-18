package com.fdh.generaldialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView



/**
 * 20181018
 */
public class WalletDialog(context: Context, style: Int) : Dialog(context,style), View.OnClickListener {


    var create_wallet: Button? = null

    var import_wallet: Button? = null

    var close:ImageView?=null


    var listener: OnBusinessDialogListener? = null

    var view: View? = null


    init {
        init()
    }



    /**
     * 初始化所有的控件
     */
    private fun init() {
        view = layoutInflater.inflate(R.layout.dialog_import_wallet, null)
        create_wallet = view!!.findViewById(R.id.dialog_create_wallet) as Button
        import_wallet = view!!.findViewById(R.id.dialog_import_wallet) as Button
        close = view!!.findViewById(R.id.dialog_close) as ImageView
        window.setWindowAnimations(R.style.dialogWindowAnim)

        create_wallet!!.setOnClickListener(this)
        import_wallet!!.setOnClickListener(this)
        close!!.setOnClickListener(this)
        setCancelable(true)
    }

    /**
     * 点击事件处理
     */
    override fun onClick(v: View?) {
        if (v?.id == R.id.dialog_create_wallet) {
            if (listener != null) {
                listener!!.click(v, CREATE_WALLET)
            }
        } else if (v?.id == R.id.dialog_import_wallet) {
            if (listener != null)
                listener!!.click(v, IMPORT_WALLET)
        }
        dismiss()
    }

    /**
     * 改变dialog的宽度 变小一点
     */
    private fun resizeDialog() {
        var params: WindowManager.LayoutParams = window.attributes;
        //TODO
        params.width = (DisplayUtil.getScreenWidth(context) * 0.75).toInt()
        window.attributes = params
    }

    override fun show() {
        addContentView(view, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        super.show()
        resizeDialog()
    }


    /**
     * 设置监听
     */
    public fun setOnBusinessDialogListener(listener: OnBusinessDialogListener) {
        this.listener = listener;
    }

    /**
     * BusinessDialog listener
     */
    public interface OnBusinessDialogListener {
        fun click(view: View, which: Int)

    }


    companion object {

        @JvmField val CREATE_WALLET: Int = 1;
        @JvmField val IMPORT_WALLET: Int = 2;

    }
}
