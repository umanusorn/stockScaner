package um.vi8e.com.stocktakescanner.zbar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import um.vi8e.com.stocktakescanner.R;

public class ScannerFragmentActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scanner_fragment);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.scanner_fragment);
        fragment.getActivity().setVisible(false);

    }
}
