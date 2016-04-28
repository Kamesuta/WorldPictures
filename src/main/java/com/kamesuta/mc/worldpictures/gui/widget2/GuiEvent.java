package com.kamesuta.mc.worldpictures.gui.widget2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

public final class GuiEvent {
	public final Map<String, List<IGuiActionListener>> events;

	public GuiEvent() {
		this.events = new HashMap<String, List<IGuiActionListener>>();
	}

	public void addEventListener(final String command, final IGuiActionListener listener) {
		Validate.notNull(command);
		Validate.notNull(listener);
		final List<IGuiActionListener> actions = getActionsOrCreate(command);
		actions.add(listener);
		this.events.put(command, actions);
	}

	public List<IGuiActionListener> removeEvent(final String command) {
		return this.events.remove(command);
	}

	public boolean removeActionListener(final String command, final IGuiActionListener listener) {
		Validate.notNull(command);
		Validate.notNull(listener);
		final List<IGuiActionListener> actions = this.events.get(command);
		if (actions != null)
			return actions.remove(listener);
		return false;
	}

	protected List<IGuiActionListener> getActionsOrCreate(final String command) {
		final List<IGuiActionListener> actions = this.events.get(command);
		if (actions != null)
			return actions;
		else
			return new ArrayList<IGuiActionListener>();
	}

	public void eventDispatch(final String command, final Object... params) {
		if (command != null) {
			final List<IGuiActionListener> actions = this.events.get(command);
			actionsDispatch(actions, command, params);
		}
	}

	protected void actionsDispatch(final List<IGuiActionListener> actions, final String command, final Object... params) {
		if (actions != null) {
			for (final IGuiActionListener action : actions) {
				actionDispatch(action, command, params);
			}
		}
	}

	protected void actionDispatch(final IGuiActionListener action, final String command, final Object... params) {
		if (action != null)
			action.actionPerformed(command, params);
	}
}
