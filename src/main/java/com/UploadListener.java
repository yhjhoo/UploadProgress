package com;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class UploadListener implements ProgressListener {
	private HttpSession session;

	public UploadListener(HttpServletRequest req) {
		session = req.getSession();
	}

	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		long megaBytes = -1;
		long mBytes = pBytesRead / 1000000;
		if (megaBytes == mBytes) {
			return;
		}
		megaBytes = mBytes;
//		System.out.println("We are currently reading item " + pItems);
		if (pContentLength == -1) {
			// System.out.println("So far, " + pBytesRead +
			// " bytes have been read.");
			session.setAttribute("status", "So far, " + pBytesRead + " of "
					+ pContentLength + " bytes have been read.");
			session.setAttribute("read", pBytesRead);
			session.setAttribute("total", pContentLength);
		} else {
			// System.out.println("So far, " + pBytesRead + " of "
			// + pContentLength + " bytes have been read.");
			session.setAttribute("status", "So far, " + pBytesRead + " of "
					+ pContentLength + " bytes have been read.");
			session.setAttribute("read", pBytesRead);
			session.setAttribute("total", pContentLength);
		}

	}

}
