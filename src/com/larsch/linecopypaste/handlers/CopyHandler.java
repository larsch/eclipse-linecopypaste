package com.larsch.linecopypaste.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

import com.larsch.linecopypaste.Utility;

/**
 * The CopyHandler replaces the default copy COPY action. If nothing is
 * currently selected, places the contents of the current line in the clipboard,
 * otherwise runs the standard COPY.
 */
public class CopyHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ITextSelection textSelection = Utility.getTextSelection(event);
		ITextEditor editor = Utility.getEditorForEvent(event);
		if (textSelection.getLength() == 0) {
			org.eclipse.swt.dnd.Clipboard cb = new org.eclipse.swt.dnd.Clipboard(
					PlatformUI.getWorkbench().getDisplay());
			IDocument doc = Utility.getCurrentDocument(event);
			try {
				IRegion line = doc.getLineInformation(textSelection
						.getStartLine());
				String text = doc.get(line.getOffset(), line.getLength());
				Object[] data = { text + "\n" };
				Transfer[] transfers = { TextTransfer.getInstance() };
				cb.setContents(data, transfers);
			} catch (BadLocationException e) {
			}
		} else {
			editor.getAction(ITextEditorActionConstants.COPY).run();
		}
		return null;
	}
}
