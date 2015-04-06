package de.plushnikov.intellij.plugin.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import de.plushnikov.intellij.plugin.Version;
import org.jetbrains.annotations.Nullable;

/**
 * Persistent global settings object for the Lombok plugin.
 */
@State(
    name = "LombokSettings",
    storages = @Storage(id = "other", file = StoragePathMacros.APP_CONFIG + "/lombok.xml")
)
public class LombokSettings implements PersistentStateComponent<LombokSettings.State> {
  static class State {
    public String donationShown = "";
  }

  /**
   * Get the instance of this service.
   *
   * @return the unique {@link LombokSettings} instance.
   */
  public static LombokSettings getInstance() {
    return ServiceManager.getService(LombokSettings.class);
  }

  private State myState = new State();

  @Nullable
  @Override
  public State getState() {
    return myState;
  }

  @Override
  public void loadState(State element) {
    myState = element;
  }

  public boolean isDonationShown() {
    return Version.PLUGIN_VERSION.equals(myState.donationShown);
  }

  public void setDonationShown() {
    myState.donationShown = Version.PLUGIN_VERSION;
  }

}
