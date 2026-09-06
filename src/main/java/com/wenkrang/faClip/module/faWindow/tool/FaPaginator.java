package com.wenkrang.faClip.module.faWindow.tool;

import com.wenkrang.faClip.module.faMessage.exception.FaWindowException;

import java.util.List;

public class FaPaginator<T> {
    private final List<T> list;

    private int page = 1;

    private final int sizeEveryPage;

    public FaPaginator(List<T> list, int sizeEveryPage) {
        if (list == null) {
            throw new FaWindowException("FaWindow.Error.Paginator.ListNull");
        }
        if (sizeEveryPage <= 0) {
            throw new FaWindowException("FaWindow.Error.Paginator.InvalidPageSize", sizeEveryPage);
        }
        this.list = list;
        this.sizeEveryPage = sizeEveryPage;
    }

    public List<T> getList() {
        return list;
    }

    public int getCurrentPage() {
        return page;
    }

    public boolean setPage(int page) {
        if (page > 0 && ((page - 1) * sizeEveryPage) < list.size()) {
            this.page = page;

            return true;
        }

        return false;
    }

    public boolean next() {
        return setPage(getCurrentPage() + 1);
    }

    public boolean previous() {
        return setPage(getCurrentPage() - 1);
    }

    public List<T> get() {
        return list.subList((page - 1) * sizeEveryPage, Math.min(page * sizeEveryPage, list.size()));
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) list.size() / sizeEveryPage);
    }

    public boolean hasNext() {
        return page < getTotalPages();
    }

    public boolean hasPrevious() {
        return page > 1;
    }
}
