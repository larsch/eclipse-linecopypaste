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

		int adjustment = 0;

		boolean lastCharIsNewLine = textData.charAt(textData.length() - 1) == 10;
		boolean selectionIsEmpty = textSelection.getLength() == 0;

		if (lastCharIsNewLine && selectionIsEmpty) {
			IDocument doc = Utility.getCurrentDocument(event);
			try {
				// Move cursor to start of line
				int lineOffset = doc
						.getLineOffset(textSelection.getStartLine());
				TextSelection ts = new TextSelection(lineOffset, 0);
				editor.getSelectionProvider().setSelection(ts);
				adjustment = lineOffset - textSelection.getOffset();
			} catch (BadLocationException e) {
			}
		}
		
		// Paste!
		editor.getAction(ITextEditorActionConstants.PASTE).run();

		// Move the cursor back where it was
		if (adjustment != 0) {
			ITextSelection newSelection = Utility.getTextSelection(event);
			TextSelection ts = new TextSelection(newSelection.getOffset()
					- adjustment, 0);
			editor.getSelectionProvider().setSelection(ts);
		}
		return null;
	}
}
