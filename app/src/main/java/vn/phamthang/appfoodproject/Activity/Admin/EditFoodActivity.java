package vn.phamthang.appfoodproject.Activity.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.phamthang.appfoodproject.Activity.BaseActivity;
import vn.phamthang.appfoodproject.Activity.MainActivity;
import vn.phamthang.appfoodproject.Domain.Foods;
import vn.phamthang.appfoodproject.Domain.Location;
import vn.phamthang.appfoodproject.R;
import vn.phamthang.appfoodproject.databinding.ActivityEditFoodBinding;

public class EditFoodActivity extends BaseActivity {
    ActivityEditFoodBinding binding;
    private Foods object;
    private int idLocation;
    private String id, title, price,time,location,descripton,imgpath;
    private boolean switchValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initLocation();
        getVeriable();

    }

    private void getVeriable() {
        binding.btBack.setOnClickListener(v -> finish());

        binding.tvIdFood.setText(object.getId()+"");
        Glide.with(EditFoodActivity.this)
                .load(object.getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(20)) // crop and border
                .into(binding.imgFood);
        binding.tvTitleFood.setText(object.getTitle());
        binding.tvPrice.setText(""+object.getPrice());
        binding.tvTime.setText(""+object.getTimeValue());
        binding.edtImgPath.setText(object.getImagePath());
        binding.tvDes.setText(object.getDescription());
        binding.btUpdateFood.setOnClickListener(v -> UpdateFoodInfomation());
        binding.switch1.setChecked(object.getBestFood());
    }

    private void UpdateFoodInfomation() {
        getValueInTextView();
        DatabaseReference myRef= database.getReference(FOODS);
        binding.progressBar.setVisibility(View.VISIBLE);
        Query query = myRef.orderByChild("Id").equalTo(object.getId());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DatabaseReference childReference = snapshot.getRef().child(String.valueOf(object.getId()));
                Double idPriceValue = convertPrice(price);
                Double idTimeValue = convertTime(time);
//                Double idLocValue = convertLoc(location);

                childReference.child("Title").setValue(title);
                childReference.child("Price").setValue(Double.parseDouble(price));
                childReference.child("PriceId").setValue(idPriceValue);
                childReference.child("TimeValue").setValue(Double.parseDouble(time));
                childReference.child("TimeId").setValue(idTimeValue);
                childReference.child("LocationId").setValue(idLocation);
                childReference.child("ImagePath").setValue(imgpath);
                childReference.child("Description").setValue(descripton);
                childReference.child("BestFood").setValue(switchValue);

                Toast.makeText(EditFoodActivity.this, "Sửa thành công", Toast.LENGTH_LONG).show();
                binding.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getValueInTextView() {
        id = binding.tvIdFood.getText().toString();
        title = binding.tvTitleFood.getText().toString();
        price = binding.tvPrice.getText().toString();
        time = binding.tvTime.getText().toString();
        location = String.valueOf(idLocation);
        imgpath = binding.edtImgPath.getText().toString();
        descripton = binding.tvDes.getText().toString();
        switchValue = binding.switch1.isChecked();
    }
  
    private Double convertTime(String time){
        int timeValue = Integer.parseInt(time);
        Double id = null;
        if(0< timeValue && timeValue <= 10) {
            id = 0.0;
        } else if(timeValue>10 && timeValue <= 30){
            id = 1.0;
        } else if (timeValue>30) {
            id = 2.0;
        }
        return  id;
    }
    private Double convertPrice(String price){
        Double priceValue = Double.parseDouble(price);
        Double id;
        if(priceValue>0.0 && priceValue<=10.0) {
            id = 0.0;
        } else if(priceValue>10.0 && priceValue<=30.0){
            id = 1.0;
        } else {
            id = 2.0;
        }
        return id;
    }

    private void getIntentExtra() {
        object = (Foods)getIntent().getSerializableExtra("items");
    }
    private void initLocation() {
        DatabaseReference myRef = database.getReference(LOCATION);
        ArrayList<Location> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Location.class));
                    }
                    ArrayAdapter<Location> adapter =new ArrayAdapter<>(EditFoodActivity.this,R.layout.sp_location,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);
                    //set selection cho spinner đúng với location của sản phẩm
                    binding.locationSp.setSelection(object.getLocationId());

                    binding.locationSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            Location selectedLoc = (Location) parentView.getSelectedItem();
                            idLocation = selectedLoc.getId();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}