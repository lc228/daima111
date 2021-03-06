/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx;

import com.badlogic.gdx.utils.Array;

/**
 * An {@link InputProcessor} that delegates to an ordered list of other
 * InputProcessors. Delegation for an event stops if a processor returns true,
 * which indicates that the event was handled.
 * 
 * @author Nathan Sweet
 */
public class InputMultiplexer implements InputProcessor {
	private Array<InputProcessor> processors = new Array(4);

	public InputMultiplexer() {
	}

	public InputMultiplexer(InputProcessor... processors) {
		for (int i = 0; i < processors.length; i++)
			this.processors.add(processors[i]);
	}

	public void addProcessor(int index, InputProcessor processor) {
		processors.insert(index, processor);
	}

	public void removeProcessor(int index) {
		processors.removeIndex(index);
	}

	public void addProcessor(InputProcessor processor) {
		processors.add(processor);
	}

	public void removeProcessor(InputProcessor processor) {
		processors.removeValue(processor, true);
	}

	/**
	 * @return the number of processors in this multiplexer
	 */
	public int size() {
		return processors.size;
	}

	public void clear() {
		processors.clear();
	}

	public void setProcessors(Array<InputProcessor> processors) {
		this.processors = processors;
	}

	public Array<InputProcessor> getProcessors() {
		return processors;
	}

	public boolean keyDown(int keycode) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).keyDown(keycode))
				return true;
		return false;
	}

	public boolean keyUp(int keycode) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).keyUp(keycode))
				return true;
		return false;
	}

	public boolean keyTyped(char character) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).keyTyped(character))
				return true;
		return false;
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).touchDown(x, y, pointer, button))
				return true;
		return false;
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).touchUp(x, y, pointer, button))
				return true;
		return false;
	}

	public boolean touchDragged(int x, int y, int pointer) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).touchDragged(x, y, pointer))
				return true;
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).touchMoved(x, y))
				return true;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).scrolled(amount))
				return true;
		return false;
	}

	@Override
	public boolean keyTyped(String characters) {
		// TODO Auto-generated method stub
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).keyTyped(characters))
				return true;
		return false;
	}
}
