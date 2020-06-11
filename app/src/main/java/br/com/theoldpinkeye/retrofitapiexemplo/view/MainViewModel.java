package br.com.theoldpinkeye.retrofitapiexemplo.view;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import br.com.theoldpinkeye.retrofitapiexemplo.BR;

public class MainViewModel extends BaseObservable {

  private String text;
  private boolean pb;

  @Bindable
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
    notifyPropertyChanged(BR.text);
  }

  @Bindable
  public boolean isPb() {
    return pb;
  }

  public void setPb(boolean pb) {
    this.pb = pb;
    notifyPropertyChanged(BR.pb);
  }
}
