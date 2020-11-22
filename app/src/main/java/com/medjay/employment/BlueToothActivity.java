package com.medjay.employment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class BlueToothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT =0;
    private static final int REQUEST_DISCOVER_BT =1;

    TextView _bluetoothStatusTv, _pairedTv;
    Button _onButton, _offButton, _discoverButton, _pairedButton;
    ImageView _bluetoothIcon;

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);

        _bluetoothStatusTv=findViewById(R.id.bluetoothStatusTv);
        _pairedTv=findViewById(R.id.pairedTv);

        _onButton=findViewById(R.id.onButton);
        _offButton=findViewById(R.id.offButton);
        _discoverButton=findViewById(R.id.discoverButton);
        _pairedButton=findViewById(R.id.pairedButton);

        _bluetoothIcon=findViewById(R.id.bluetoothIcon);

        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter==null){

            _bluetoothStatusTv.setText("bluetooth is not available");

        }else {

            _bluetoothStatusTv.setText("bluetooth is available");

        }

        if (bluetoothAdapter.isEnabled()){

            _bluetoothIcon.setImageResource(R.drawable.ic_bluetooth_on);

        }else {

            _bluetoothIcon.setImageResource(R.drawable.ic_bluetooth_off);

        }

        _onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!bluetoothAdapter.isEnabled()){

                    ToastMessage("turning on bluetooth...");

                    Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_ENABLE_BT);

                }else {

                    ToastMessage("bt already on....");

                }
            }
        });

        _offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bluetoothAdapter.isEnabled()){

                    ToastMessage("turning off bluetooth...");
                    bluetoothAdapter.disable();
                    _bluetoothIcon.setImageResource(R.drawable.ic_bluetooth_off);

                }else {

                    ToastMessage("bt already off....");

                }
            }
        });

        _discoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!bluetoothAdapter.isDiscovering()){

                    ToastMessage("making your device discoverable");

                    Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);

                }else {

                    ToastMessage("bt already on....");

                }

            }
        });

        _pairedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bluetoothAdapter.isEnabled()){

                    _pairedTv.setText("paired devices");
                    Set<BluetoothDevice> devices =bluetoothAdapter.getBondedDevices();

                    for (BluetoothDevice device: devices){

                        _pairedTv.append("\nDevice"+ device.getName()+", "+device);

                    }
                }else {

                    ToastMessage("turn on bluetooth");

                }



            }
        });

    }

    public void ToastMessage(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case REQUEST_ENABLE_BT: if (resultCode == RESULT_OK){

                _bluetoothIcon.setImageResource(R.drawable.ic_bluetooth_on);
                ToastMessage("bt enabled");

            }else {
                ToastMessage("couldn't enable bt");
            }

            break;

        }
    }
}
