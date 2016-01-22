package org.aaronhe.rxgooglemapsbinding;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaView;
import rx.Observable;
import rx.Subscriber;

final class StreetViewPanoramaViewPanoramaReadyOnSubscribe
    implements Observable.OnSubscribe<StreetViewPanorama> {
  final StreetViewPanoramaView view;

  StreetViewPanoramaViewPanoramaReadyOnSubscribe(StreetViewPanoramaView view) {
    this.view = view;
  }

  @Override public void call(final Subscriber<? super StreetViewPanorama> subscriber) {
    OnStreetViewPanoramaReadyCallback callback = new OnStreetViewPanoramaReadyCallback() {
      @Override public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(streetViewPanorama);
        }
      }
    };

    view.getStreetViewPanoramaAsync(callback);
  }
}
