package com.example.weighttracker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

public class SmsPermission extends DialogFragment {
    private static final int SMS_PERMISSION_CODE = 101;
    private SmsPermissionListener listener;

    public interface SmsPermissionListener {
        void onSmsPermissionGranted();
        void onSmsPermissionDenied();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SmsPermissionListener) {
            listener = (SmsPermissionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SmsPermissionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sms_permission, container, false);

        Button allowButton = view.findViewById(R.id.allowButton);
        Button denyButton = view.findViewById(R.id.denyButton);

        allowButton.setOnClickListener(v -> requestSmsPermission());
        denyButton.setOnClickListener(v -> dismiss());

        return view;
    }

    private void requestSmsPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        } else {
            // Permission already granted, notify the listener
            if (listener != null) {
                listener.onSmsPermissionGranted();
            }
            dismiss();
        }
    }

    private void sendSmsNotification(String message) {
        String phoneNumber = "1234567890";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        Toast.makeText(getContext(), "SMS sent: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSmsNotification("Your goal weight has been reached!");
                if (listener != null) {
                    listener.onSmsPermissionGranted();
                }
            } else {
                Toast.makeText(getContext(), "SMS permission denied. Notifications will not be sent.", Toast.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.onSmsPermissionDenied();
                }
            }
            dismiss();
        }
    }
}
