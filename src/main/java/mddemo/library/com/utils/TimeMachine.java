package mddemo.library.com.utils;

import android.os.Handler;

public class TimeMachine {

  private static Handler handler = new Handler();

  private TimeMachine() {
  }

  public static void sendMessageToTheFuture(final Runnable runnable, final int delay) {
    handler.postDelayed(runnable, delay);
  }

  public static void cancelMessage(Runnable runnable) {
    handler.removeCallbacks(runnable);
  }
}
