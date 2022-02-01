package com.reactnativesmartrede;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.util.HashMap;
import java.util.Map;

import rede.smartrede.sdk.FlexTipoPagamento;

import rede.smartrede.sdk.RedePaymentValidationError;
import rede.smartrede.sdk.RedePayments;

@ReactModule(name = SmartRedeModule.NAME)
public class SmartRedeModule extends ReactContextBaseJavaModule {
  public static final String NAME = "SmartRede";

  private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";
  private static final int PAYMENT_REQUEST_CODE = 0;
  private RedePayments redePayments;

  public SmartRedeModule(ReactApplicationContext reactContext) {
    super(reactContext);
    redePayments = RedePayments.getInstance(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  public void handleRede(String type, int value, int installments, Promise promise) {
    Activity currentActivity = getCurrentActivity();
    if (currentActivity == null) {
      promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
      return;
    }
    try {
      Intent collectPaymentIntent = collectPaymentIntent = redePayments.intentForPaymentBuilder(FlexTipoPagamento.CREDITO_PARCELADO_EMISSOR, 500).setInstallments(4).build();
      currentActivity.startActivityForResult(collectPaymentIntent, PAYMENT_REQUEST_CODE);
      promise.resolve("Sucesso");
    }catch (ActivityNotFoundException ex){
      promise.reject("error", ex.getMessage());
    }catch (RedePaymentValidationError ex){
      promise.reject("error", ex.getMessage());
    }
  }
}
