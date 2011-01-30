package com.larsch.linecopypaste.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

import com.larsch.linecopypaste.Utility;

/**
 * The paste handler wraps the default paste action. If the contents of the
 * clipboard contains a newline at the end, and nothing is currently selected,
 * the cursor is moved to the start of the line to past the lines as is, rather
 * than in the middle of the text.
 */
public class PasteHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ITextEditor editor = Utility.getEditorForEvent(event);
		org.eclipse.swt.dnd.Clipboard cb = new org.eclipse.swt.dnd.Clipboard(
				PlatformUI.getWorkbench().getDisplay());
		TextTransfer tt = TextTransfer.getInstance();
		cb.getContents(tt);
		String textData = (String) cb.getContents(tt);

		ITextSelection textSelection = Utility.getTextSelection(event);
		if (textData.charAt(textData.length() - 1) == 10
				&& textSelection.getLength() == 0) {
			IDocument doc = Utility.getCurrentDocument(event);
			try {
				TextSelection ts = new TextSelection(
						doc.getLineOffset(textSelection.getStartLine()), 0);
				editor.getSelectionProvider().setSelection(ts);
			} catch (BadLocationException e) {
			}
		}
		editor.getAction(ITextEditorActionConstants.PASTE).run();
		return null;
	}
}
