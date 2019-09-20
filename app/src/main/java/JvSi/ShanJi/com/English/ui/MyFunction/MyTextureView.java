package JvSi.ShanJi.com.English.ui.MyFunction;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;

import java.net.URISyntaxException;

import JvSi.ShanJi.com.English.Utils.UploadPic;

/**
 * **************************************************************************************************
 * 修改日期                         修改人             任务名称                         功能或Bug描述
 * 2018/10/12 10:36                 MUZI102                                             TextureView类目
 * **************************************************************************************************
 */
public class MyTextureView extends TextureView implements View.OnLayoutChangeListener {
    public Camera mCamera;
    private Context context;
    private Camera.Parameters param;
    private boolean isCanTakePicture = false;
    Matrix matrix;
    Camera camera;
    int mWidth = 0;
    int mHeight = 0;
    int mDisplayWidth = 0;
    int mDisplayHeight = 0;
    int mPreviewWidth = 640;
    int mPreviewHeight = 480;
    int orientation = 0;

    public MyTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        if (null == mCamera) {
            mCamera = Camera.open();
        }
        this.setSurfaceTextureListener(new SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
                param = mCamera.getParameters();
                param.setPictureFormat(PixelFormat.JPEG);
                param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                if (!Build.MODEL.equals("KORIDY H30")) {
                    param.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 1连续对焦
                } else {
                    param.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                }
                mCamera.setParameters(param);
                //变形处理
                RectF previewRect = new RectF(0, 0, mWidth, mHeight);
                double aspect = (double) mPreviewWidth / mPreviewHeight;
                if (getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_PORTRAIT) {
                    aspect = 1 / aspect;
                }
                if (mWidth < (mHeight * aspect)) {
                    mDisplayWidth = mWidth;
                    mDisplayHeight = (int) (mHeight * aspect + .5);
                } else {
                    mDisplayWidth = (int) (mWidth / aspect + .5);
                    mDisplayHeight = mHeight;
                }
                RectF surfaceDimensions = new RectF(0, 0, mDisplayWidth, mDisplayHeight);
                Matrix matrix = new Matrix();
                matrix.setRectToRect(previewRect, surfaceDimensions, Matrix.ScaleToFit.FILL);
                MyTextureView.this.setTransform(matrix);
                //<-处理变形
                int displayRotation = 0;
                WindowManager windowManager = (WindowManager) context
                        .getSystemService(Context.WINDOW_SERVICE);
                int rotation = windowManager.getDefaultDisplay().getRotation();
                switch (rotation) {
                    case Surface.ROTATION_0:
                        displayRotation = 0;
                        break;
                    case Surface.ROTATION_90:
                        displayRotation = 90;
                        break;
                    case Surface.ROTATION_180:
                        displayRotation = 180;
                        break;
                    case Surface.ROTATION_270:
                        displayRotation = 270;
                        break;
                }
                Camera.CameraInfo info = new Camera.CameraInfo();
                Camera.getCameraInfo(0, info);
                int orientation;
                if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    orientation = (info.orientation - displayRotation + 360) % 360;
                } else {
                    orientation = (info.orientation + displayRotation) % 360;
                    orientation = (360 - orientation) % 360;
                }
                mCamera.setParameters(param);
                mCamera.setDisplayOrientation(orientation);
                try {
                    mCamera.setPreviewTexture(surfaceTexture);
                    mCamera.startPreview();
                    isCanTakePicture = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                if (mCamera != null) {
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                    isCanTakePicture = true;
                }
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }
        });
    }

    /**
     * 拍照
     */
    public void take() {
        if (mCamera != null && isCanTakePicture) {
            isCanTakePicture = false;
            long curClickTime = System.currentTimeMillis();
            Log.d("timebbb","curClickTime2"+curClickTime);
            mCamera.takePicture(new Camera.ShutterCallback() {
                @Override
                public void onShutter() {

                }
            }, null, mPictureCallback);
        }
    }

    public void startPreview() {
        if (mCamera != null && !isCanTakePicture) {
            MyTextureView.this.setBackgroundDrawable(null);
            mCamera.startPreview();
            isCanTakePicture = true;
        }
    }

    public void stopPreview() {
        if (mCamera != null) {
            mCamera.stopPreview();
        }
    }


    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            if (mCamera != null) {
                //mCamera.stopPreview();
                startPreview();
                long curClickTime = System.currentTimeMillis();
                Log.d("timebbb","curClickTime3"+curClickTime);
                new FileSaver(data).save();
            }
        }
    };

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        mWidth = right - left;
        mHeight = bottom - top;
    }

    private class FileSaver implements Runnable {
        private byte[] buffer;

        public FileSaver(byte[] buffer) {
            this.buffer = buffer;
        }

        public void save() {
            long curClickTime = System.currentTimeMillis();
            Log.d("timebbb","curClickTime4"+curClickTime);
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                long curClickTime = System.currentTimeMillis();
                Log.d("timebbb2",">>>>>>>>>>>>>>>>>>curClickTime1                "+curClickTime);
                //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
               //         System.currentTimeMillis() + ".png");
                //file.createNewFile();
                //FileOutputStream os = new FileOutputStream(file);
                //BufferedOutputStream bos = new BufferedOutputStream(os);
                Bitmap bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
                //bitmap.compress(Bitmap.CompressFormat.PNG, 4, bos);
                long curClickTime3 = System.currentTimeMillis();
                Log.d("timebbb2",">>>>>>>>>>>>>>>>>>curClickTime133333333       "+curClickTime3);
                //bos.flush();
                //bos.close();
                //os.close();
                //Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null,null));
                try {
                    UploadPic.uploadPic(UploadPic.getFile(bitmap));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                //MyTextureView.this.setBackgroundDrawable(new BitmapDrawable(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
