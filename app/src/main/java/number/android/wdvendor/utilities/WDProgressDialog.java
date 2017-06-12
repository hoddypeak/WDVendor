package number.android.wdvendor.utilities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import number.android.wdvendor.R;

/**
 * Created by user on 12/22/2016.
 */

public class WDProgressDialog extends Dialog {

    View view;
    TextView messageTxtView;
    public WDProgressDialog(Context context) {
        super(context);
        LayoutInflater inflater = this.getLayoutInflater();
        view =  inflater.inflate(R.layout.material_progress_indicator, null);
        this.setCanceledOnTouchOutside(false);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setContentView(view);
    }

    public void setMessage(String message){
        messageTxtView = (TextView) view.findViewById(R.id.message);
        messageTxtView.setText(message);
    }

    /**
     *  Dismiss Wd Progress Dialog
     */
    public void dismissWdProgressDialog(){
        if(this.isShowing())
            this.dismiss();
    }
}
