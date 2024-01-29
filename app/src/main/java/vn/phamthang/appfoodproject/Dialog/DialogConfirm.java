package vn.phamthang.appfoodproject.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import vn.phamthang.appfoodproject.Activity.MainActivity;
import vn.phamthang.appfoodproject.Interface.OnCustomDialogClickListener;
import vn.phamthang.appfoodproject.R;

public class DialogConfirm {

    private Dialog dialog;
    private Context context;

    public DialogConfirm(Context context) {
        this.context = context;
        // Create the Dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.diallog_base);

        dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.background_dialog));

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button okayButton = dialog.findViewById(R.id.buttonYes);
        Button cancelButton = dialog.findViewById(R.id.buttonNo);

        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOkayAction();
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }
    public void performOkayAction(){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}