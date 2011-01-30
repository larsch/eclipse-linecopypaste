package com.larsch.linecopypaste.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

import com.larsch.linecopypaste.Utility;

/**
 * The CutHandler wraps the default cut actions CUT and CUT_LINE. If nothing is
 * currently selected, CUT_LINE is executed, otherwise the standard CUT.
 */
public class CutHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ITextSelection textSelection = Utility.getTextSelection(event);
		ITextEditor editor = Utility.getEditorForEvent(event);
		if (textSelection.getLength() == 0) {
			editor.getAction(ITextEditorActionConstants.CUT_LINE).run();
		} else {
			editor.getAction(ITextEditorActionConstants.CUT).run();
		}
		return null;
	}
}
