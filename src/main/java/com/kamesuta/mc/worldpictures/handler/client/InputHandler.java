package com.kamesuta.mc.worldpictures.handler.client;

import org.lwjgl.input.Keyboard;

import com.kamesuta.mc.worldpictures.gui.GuiWorldPictures;
import com.kamesuta.mc.worldpictures.proxy.ClientProxy;
import com.kamesuta.mc.worldpictures.reference.Names;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.settings.KeyBinding;

public class InputHandler {
	public static final InputHandler INSTANCE = new InputHandler();

    private static final KeyBinding KEY_BINDING_GUI = new KeyBinding(Names.Keys.GUI, Keyboard.KEY_DIVIDE, Names.Keys.CATEGORY);
    private static final KeyBinding KEY_BINDING_ACTION = new KeyBinding(Names.Keys.ACTION, Keyboard.KEY_SUBTRACT, Names.Keys.CATEGORY);
	public static final KeyBinding[] KEY_BINDINGS = new KeyBinding[] { KEY_BINDING_GUI, KEY_BINDING_ACTION };

	private InputHandler() {
	}

	@SubscribeEvent
	public void onKeyInput(InputEvent event) {
		if (ClientProxy.MINECRAFT.currentScreen == null) {
			if (KEY_BINDING_GUI.isPressed()) {
				ClientProxy.MINECRAFT.displayGuiScreen(new GuiWorldPictures(ClientProxy.MINECRAFT.currentScreen));
			}
			if (KEY_BINDING_ACTION.isPressed()) {

			}
		}
	}
}
