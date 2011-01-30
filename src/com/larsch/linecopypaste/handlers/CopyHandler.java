package com.larsch.linecopypaste.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

import com.larsch.linecopypaste.Utility;

/**
 * The CopyHandler wraps the default copy actions COPY and COPY_LINE_DOWN. If
 * nothing is currently selected, COPY_LINE_DOWN is executed, otherwise the
 * standard COPY.
 */
public class CopyHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ITextSelection textSelection = Utility.getTextSelection(event);
		ITextEditor editor = Utility.getEditorForEvent(event);
		if (textSelection.getLength() == 0) {
			editor.getAction(ITextEditorActionConstants.COPY_LINE_DOWN).run();
		} else {
			editor.getAction(ITextEditorActionConstants.COPY).run();
		}
		return null;
	}
}
