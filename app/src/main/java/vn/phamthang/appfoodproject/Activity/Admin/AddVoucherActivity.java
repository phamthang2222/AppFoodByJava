package vn.phamthang.appfoodproject.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import vn.phamthang.appfoodproject.Activity.User.BaseActivity;
import vn.phamthang.appfoodproject.Objects.Voucher;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityAddVoucherBinding;

public class AddVoucherActivity extends BaseActivity {
    ActivityAddVoucherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intiView();
    }

    private void intiView() {
        binding.btBack.setOnClickListener(v -> {
            startActivity(new Intent(this, ManagementVoucherActivity.class));
            finish();

        });
        binding.btnAddNewVoucher.setOnClickListener(v -> {
            addVoucher();
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        });

    }

    private void addVoucher() {
        String idVoucher = database.getReference("Voucher").push().getKey();

        String code = binding.edtCodeVoucher.getText().toString();
        String value = binding.edtValueVoucher.getText().toString();
//        Double valueVoucher = Double.valueOf(value);

        Voucher newVoucher = new Voucher(idVoucher, code, value);

        database.getReference("Voucher").child(idVoucher).setValue(newVoucher);

    }
}