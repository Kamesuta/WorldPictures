package com.kamesuta.mc.worldpictures.gui;

import com.kamesuta.mc.worldpictures.guiwidget.GuiCCButton;
import com.kamesuta.mc.worldpictures.guiwidget.GuiCCTextField;
import com.kamesuta.mc.worldpictures.guiwidget.GuiScreenWidget;
import com.kamesuta.mc.worldpictures.reference.Reference;

public class GuiSceneMaker extends GuiScreenWidget {
	public void initGui() {
		super.initGui();
		GuiCCButton widget = new GuiCCButton(0, 0, 100, 20, "Hello");
				widget.setActionCommand("actionWidgetPerformedKey");
				this.add(widget);
		GuiCCTextField textfield = new GuiCCTextField(0, 30, 100, 20, "Hello");
				textfield.setActionCommand("actionWidgetPerformedKew");
				this.add(textfield);
	}

	@Override
	public void actionPerformed(String ident, Object... params) {
		Reference.logger.info(ident);
	}
}
