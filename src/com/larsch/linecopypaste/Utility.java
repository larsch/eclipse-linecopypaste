package com.larsch.linecopypaste;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Utility methods for the LineClipboard plugin (stolen from nflath's plugin at
 * https://github.com/nflath/eclipse-hungry-delete/)
 */
public class Utility {

	public static ITextEditor getEditorForEvent(ExecutionEvent event)
			throws ExecutionException {
		IEditorPart part = HandlerUtil.getActiveEditorChecked(event);
		if (part instanceof ITextEditor)
		{
			return (ITextEditor) part;
		}
		else if (part instanceof MultiPageEditorPart)
		{
			MultiPageEditorPart multiPart = (MultiPageEditorPart)part;
			Object page = multiPart.getSelectedPage();
			if (page instanceof ITextEditor)
			{
				return (ITextEditor)page;
			}
		}
		return null;
	}

	public static IDocument getCurrentDocument(ExecutionEvent event)
			throws ExecutionException {
		IDocumentProvider dp = getEditorForEvent(event).getDocumentProvider();
		return dp.getDocument(getEditorForEvent(event).getEditorInput());
	}

	public static ITextSelection getTextSelection(ExecutionEvent event)
			throws ExecutionException {
		return (ITextSelection) getEditorForEvent(event).getSelectionProvider()
				.getSelection();
	}

	public static int getCurrentOffset(ExecutionEvent event)
			throws ExecutionException {
		return getTextSelection(event).getOffset();
	}

	public static boolean selectionDefined(ExecutionEvent event)
			throws ExecutionException {
		return getTextSelection(event).getLength() != 0;
	}

}
