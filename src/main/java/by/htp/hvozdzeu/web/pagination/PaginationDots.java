package by.htp.hvozdzeu.web.pagination;

import java.util.ArrayList;
import java.util.List;

class PaginationDots {
	
	private static final String DOTS_BTN = "...";
	private static final Integer DELTA = 2;

	private PaginationDots() {
	}

	static List<String> paginationDots(int currentPage, int pageAmount) {

		Integer left = currentPage - DELTA;
		Integer right = currentPage + DELTA + 1;
		List<String> range = new ArrayList<>();
		List<String> rangeWithDots = new ArrayList<>();
		Integer l = 0;

		for (int i = 1; i <= pageAmount; i++) {
			if (i == 1 || i == pageAmount || i >= left && i < right) {
				range.add("" + i);
			}
		}

		for (String i : range) {
			if (l > 0) {
				if (Integer.parseInt(i) - l == 2) {
					rangeWithDots.add("" + (l + 1));
				} else if (Integer.parseInt(i) - l != 1) {
					rangeWithDots.add(DOTS_BTN);
				}
			}
			rangeWithDots.add(i);
			l = Integer.parseInt(i);
		}

		return rangeWithDots;
	}

}
