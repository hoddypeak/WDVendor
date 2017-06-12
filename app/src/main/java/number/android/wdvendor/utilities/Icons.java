package number.android.wdvendor.utilities;

import android.content.Context;
import android.graphics.Color;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

/**
 * Created by Pandiyarajan
 * Number
 * Waterdop
 */
public class Icons {

    Context context;

    public static final String primaryColor = "#2FA8EC";
    public static final String white = "#FFFFFF";
    public static final String grey_medium = "#8F8F8F";

    public Icons(Context context) {
        this.context = context;
    }

    public IconicsDrawable tickIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_check)
                .color(Color.WHITE)
                .sizeDp(20);
    }

    public IconicsDrawable plusIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_plus)
                .color(Color.WHITE)
                .sizeDp(20);
    }

    public IconicsDrawable textCommentIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_comment_text)
                .color(Color.GRAY)
                .sizeDp(20);
    }

    public IconicsDrawable personIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_account)
                .color(Color.parseColor(primaryColor))
                .sizeDp(14);
    }

    public IconicsDrawable mobileIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_phone)
                .color(Color.parseColor(primaryColor))
                .sizeDp(14);
    }

    public IconicsDrawable homeIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_home)
                .color(Color.parseColor(primaryColor))
                .sizeDp(14);
    }

    public IconicsDrawable arrowRightIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_arrow_right)
                .color(Color.parseColor(white))
                .sizeDp(14);
    }

    public IconicsDrawable settingsIcon(int color){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_settings)
                .color(Color.parseColor(primaryColor))
                .sizeDp(16);
    }

    public IconicsDrawable receiptIcon(int color){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_receipt)
                .color(Color.parseColor(primaryColor))
                .sizeDp(16);
    }

    public IconicsDrawable refreshIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_refresh_sync)
                .color(Color.parseColor(white))
                .sizeDp(24);
    }

    public IconicsDrawable shareIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_share)
                .color(Color.parseColor(primaryColor))
                .sizeDp(12);
    }

    public IconicsDrawable trashIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_delete)
                .color(Color.parseColor(primaryColor))
                .sizeDp(12);
    }

    public IconicsDrawable customerIcon(int color){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_accounts)
                .color(color)
                .sizeDp(16);
    }

    public IconicsDrawable customerAddIcon(int color){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_account_add)
                .color(color)
                .sizeDp(18);
    }

    public IconicsDrawable customerRejectIcon(int color){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_close)
                .color(color)
                .sizeDp(16);
    }


    public IconicsDrawable calendarIcon(){
        return new IconicsDrawable(context)
                .icon(MaterialDesignIconic.Icon.gmi_calendar)
                .color(Color.parseColor(white))
                .sizeDp(24);
    }

}
