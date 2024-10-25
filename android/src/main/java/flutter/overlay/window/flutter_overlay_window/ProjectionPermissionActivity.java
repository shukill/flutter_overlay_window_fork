package flutter.overlay.window.flutter_overlay_window;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.util.Log;

public class ProjectionPermissionActivity extends Activity {
    private static final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MediaProjectionManager projectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        Intent permissionIntent = projectionManager.createScreenCaptureIntent();
        startActivityForResult(permissionIntent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                Log.i("SS_PROCESS", "onActivityResult " + resultCode + " - " + data + " - " + data.getExtras());
                // Broadcast the result to the Service
                Intent intent = new Intent("ACTION_CAPTURE_PERMISSION_RESULT");
                intent.putExtra("resultCode", resultCode);
                intent.putExtra("data", data);
                sendBroadcast(intent);
                moveTaskToBack(true);
            }
        }
        finish();
    }
}

