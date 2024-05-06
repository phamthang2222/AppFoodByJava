package vn.phamthang.appfoodproject.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.Button;

import vn.phamthang.appfoodproject.Activity.Admin.EditFoodActivity;
import vn.phamthang.appfoodproject.Activity.Admin.FoodManagermentActivity;
import vn.phamthang.appfoodproject.R;

public class DialogConfirmEditText {

    private Dialog dialog;
    private Context context;

    public DialogConfirmEditText(Context context) {
        this.context = context;
        // Create the Dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.diallog_base);

        dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.background_dialog));

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button okayButton = dialog.findViewById(R.id.buttonYes);
        Button cancelButton = dialog.findViewById(R.id.buttonNo);

        okayButton.setOnClickListener(v -> {
            performOkayAction();
            dialog.dismiss();
        });

        cancelButton.setOnClickListener(v ->
                dialog.dismiss());
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }
    public void performOkayAction(){
        Intent intent = new Intent(context, FoodManagermentActivity.class);
        context.startActivity(intent);
        ((EditFoodActivity)context).finish();
    }
}