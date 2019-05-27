package PageDTO;

public class PageMaker {
	private int totalcount;// 전체 게시물 개수
	private int pagenum;// 현재 페이지 번호
	private int contentnum = 20;// 한 페이지에 몇개 표시할지
	private int startPage = 1; // 현재 페이지 블록의 시작 페이지
	private int endPage = 5; // 현재 페이지의 블록의 마지막 페이지
	private boolean prev = false; // 이전 페이지로 가는 화살표
	private boolean next; // 다음 페이지로 가는 화살표
	private int currentblock; // 현재 페이지 블록
	private int lastblock; // 마지막 페이지 블록

	public void prevnext(int pagenum) {
		if (getLastblock() == getCurrentblock() && getLastblock() == 1) {
			setPrev(false);
			setNext(false);

		} else if (pagenum > 0 && pagenum < 6) {
			// 현제 페이지 블럭이 1 ~5 페이지 라면
			setPrev(false);
			// 이전 화살표는 false
			setNext(true);
			// 다음 화살표는 true
		} else if (getLastblock() == getCurrentblock()) {
			// 마지막 블록 == 현제 페이지 번호
			setPrev(true);
			setNext(false);
		} else {
			setPrev(true);
			setNext(true);
		}
	}

	public int calcpage(int totalcount, int contentnum) { // 전체 페이지 수를 계산하는 함수

		// ex 125 / 10 => 12.5
		// 13 페이지

		// 50 / 10 => 5
		// 5페이지
		int totalpage = totalcount / contentnum;
		if (totalcount % contentnum > 0) {
			totalpage++;
		}
		return totalpage;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getContentnum() {
		return contentnum;
	}

	public void setContentnum(int contentnum) {
		this.contentnum = contentnum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int currentblock) { // 시작 페이지
		this.startPage = (currentblock * 5) - 4;
		// 한 블럭에 페이지가 5개가 있다면
		// 1번 블럭에 대한 첫 페이지는
		// 1 * 5 = 5 -4 = 1페이지
		// 2번 블럭에 대한 첫 페이지는
		// 2 * 5 = 10 -4 = 6페이지

		// 1// 1 2 3 4 5
		// 2 // 6 7 8 9 10
		// 3 // 11 12 13

		// 만약 한 블럭에 페이지가 10개가 있다면
		// this.startPage = (currentblock * 10) - 9;
		// 1 * 10 = 10 - 9 = 1 이렇게 시작페이지를 구할 수 있다.
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int getlastblock, int getcurrentblock) { // 끝 페이지
		if (getlastblock == getcurrentblock) {
			// 마지막 페이지 블록 번호 == 현재 페이지 블록 번호
			// 현제 페이지 블록이 마지막 블록이면
			this.endPage = calcpage(getTotalcount(), getContentnum());
			System.out.println("PageMakerVO");
			// 전체 페이지 블록 갯수
			// ex) Totalcount 갯수가 125개 이거나 128개라면, Contentnum 의 갯수는 13개
			// Contentnum 13번째 페이지를 endPage에 저장
		} else {
			this.endPage = getStartPage() + 4;
			// 시작 페이지를 구하고 거기에 +4를 한다
			// ex) 한 블럭에 10개 페이지가 있다면 +9를 하면 된다.
		}
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getCurrentblock() {
		return currentblock;
	}

	public void setCurrentblock(int pagenum) { // 현재 페이지 블록
		// 페이지 번호를 통해서 구한다
		// 페이지 번호 / 페이지 블록안의 페이지 개수
		// 1p 1 / 5 => 0.2 ==> 0 +1 페이지 블록 1
		// 3p 3 / 5 => 0.xx => 0 + 1 페이지 블록 1
		// 8p 8 / 5 => 1.6 => 1 + 1 페이지 블록 2
		this.currentblock = pagenum / 5;
		if (pagenum % 5 > 0) {
			this.currentblock++;
		}
	}

	public int getLastblock() {
		return lastblock;
	}

	public void setLastblock(int totalcount) { // 마지막 페이지 블록
		// 한 페이지당 10개 , 한 블록당 5개 블록 = 10 * 5 => 50
		// 125 / 50 ==> 페이지 블럭 3개까지 존재.
		this.lastblock = totalcount / (5 * this.contentnum);
		if (totalcount % (5 * this.contentnum) > 0) {
			this.lastblock++;
		}
	}

}
