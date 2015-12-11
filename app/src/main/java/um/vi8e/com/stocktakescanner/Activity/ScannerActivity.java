package um.vi8e.com.stocktakescanner.Activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import um.vi8e.com.stocktakescanner.Activity.viewStockTake.StocktakeModel;
import um.vi8e.com.stocktakescanner.Activity.viewStockTakeResult.StocktakeresultModel;
import um.vi8e.com.stocktakescanner.provider.stocktake.StocktakeColumns;
import um.vi8e.com.stocktakescanner.provider.stocktakeresult.StocktakeresultColumns;
import um.vi8e.com.stocktakescanner.utils.Const;

public class ScannerActivity extends ActionBarActivity implements ZXingScannerView.ResultHandler {
private ZXingScannerView mScannerView;
int i =1;
@Override
public void handleResult(Result rawResult) {
  /*  Toast.makeText(this, "Contents = " + rawResult.getText() +
                         ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();*/
  Bundle extras;
  String stocktakeId;
  try{
    extras=getIntent().getExtras();
    stocktakeId= extras.getString(StocktakeColumns._ID);
  }catch (NullPointerException e){
    stocktakeId=null;
  }

    Toast.makeText(this,"Scanned "+i+":"+rawResult.getText(),Toast.LENGTH_SHORT).show();
    i++;
    mScannerView.startCamera();
    StocktakeresultModel stocktakeresultModel= saveToDBFromStartStockTake(getApplicationContext(),
                                                                          rawResult.getText(),
                                                                          stocktakeId);
    //IntentCaller.barcodeTv(this, stocktakeresultModel);
    StartStockTakeActivity.isFinished=true;

    //finish();
}

@Override
public void onCreate(Bundle state) {
    super.onCreate(state);
    mScannerView = new ZXingScannerView(this) {
        @Override
        protected IViewFinder createViewFinderView(Context context) {
            return new CustomViewFinderView(context);
        }
    };

    setContentView(mScannerView);
  //mScannerView.startCamera(mCameraId);
  //mScannerView.setFlash(mFlash);
  mScannerView.setAutoFocus(true);
}


@Override
public void onResume() {
    super.onResume();
    mScannerView.setResultHandler(this);
    mScannerView.startCamera();
}

@Override
public void onPause() {
    super.onPause();
    mScannerView.stopCamera();
}

public static StocktakeresultModel saveToDBFromStartStockTake(Context context, String barcode, String stocktakeId) {
	String location = StartStockTakeActivity.locationEditText.getText().toString();
	String dateScanned = StartStockTakeActivity.setDateTv.getText().toString();
  return saveToDB(context, barcode, stocktakeId, location, dateScanned);
}

@NonNull public static StocktakeresultModel saveToDB(Context context,
                                                      String barcode,
                                                      String stocktakeId,
                                                      String location,
                                                      String dateScanned)
{
  StocktakeModel stocktakeModel;

  StocktakeresultModel
      stocktakeresultModel;
  if(stocktakeId==null){
    stocktakeModel = new StocktakeModel(dateScanned, "timeEnd", Const.Status.COMPLETED, location, "User Um",
                                                       "DeviceDetail");
     Uri uri= context.getContentResolver().insert(StocktakeColumns.CONTENT_URI, stocktakeModel.getValues());
    stocktakeresultModel =
        new StocktakeresultModel(uri.getPathSegments().get(1),
                                 barcode,
                                 "1",
                                 dateScanned);
    context.getContentResolver().insert(StocktakeresultColumns.CONTENT_URI, stocktakeresultModel.getValues());
  }else {
    stocktakeresultModel =
        new StocktakeresultModel(stocktakeId,
                                 barcode,
                                 "1",
                                 dateScanned);
    context.getContentResolver().insert(StocktakeresultColumns.CONTENT_URI, stocktakeresultModel.getValues());
  }

  return stocktakeresultModel;
}


private static class CustomViewFinderView extends ViewFinderView {
        public static final String TRADE_MARK_TEXT = " ";
        public static final int TRADE_MARK_TEXT_SIZE_SP = 40;
        public final Paint PAINT = new Paint();

        public CustomViewFinderView(Context context) {
            super(context);
            init();
        }

        public CustomViewFinderView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            PAINT.setColor(Color.WHITE);
            PAINT.setAntiAlias(true);
            float textPixelSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    TRADE_MARK_TEXT_SIZE_SP, getResources().getDisplayMetrics());
            PAINT.setTextSize(textPixelSize);
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawTradeMark(canvas);
        }

        private void drawTradeMark(Canvas canvas) {
            Rect framingRect = getFramingRect();
            float tradeMarkTop;
            float tradeMarkLeft;
            if (framingRect != null) {
                tradeMarkTop = framingRect.bottom + PAINT.getTextSize() + 10;
                tradeMarkLeft = framingRect.left;
            } else {
                tradeMarkTop = 10;
                tradeMarkLeft = canvas.getHeight() - PAINT.getTextSize() - 10;
            }
            canvas.drawText(TRADE_MARK_TEXT, tradeMarkLeft, tradeMarkTop, PAINT);
        }
    }
}
