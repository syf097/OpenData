package openeye;

import java.util.Arrays;

import openeye.logic.LoadDataStore;
import openeye.logic.ModMetaStore;
import argo.format.JsonFormatter;
import argo.format.PrettyJsonFormatter;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;

public class Mod extends DummyModContainer {

	public Mod() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "OpenEye";
		meta.name = "OpenEye";
		meta.version = "@VERSION@";
		meta.authorList = Arrays.asList("boq", "Mikee");
		meta.url = "http://openmods.info/";
		meta.description = "We see you...";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	private static final JsonFormatter JSON_SERIALIZER = new PrettyJsonFormatter();

	@Subscribe
	public void onModConstruct(FMLConstructionEvent evt) {
		ModMetaStore.instance.populate(LoadDataStore.instance, evt.getASMHarvestedData());
		System.out.println(JSON_SERIALIZER.format(ModMetaStore.instance.dumpAllFiles()));
	}

}
