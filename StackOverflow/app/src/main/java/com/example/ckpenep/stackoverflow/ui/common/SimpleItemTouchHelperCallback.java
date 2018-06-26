package com.example.ckpenep.stackoverflow.ui.common;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderHistoryFactory;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        if (viewHolder instanceof CrimeListFragment.CrimeAdapter.ItemViewHolder) {
//            CrimeListFragment.CrimeAdapter.ItemViewHolder holder = (CrimeListFragment.CrimeAdapter.ItemViewHolder) viewHolder;
//            // 32 — вправо, а 16 - влево. Определено эксперементальным путём
//            if (direction == 32) {
//                // вызываете ныжные вам методы
//                holder.onSwipedRight();
//            } else {
//            }
//        }
//    }

    private final ItemTouchHelperAdapter mAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof ViewHolderHistoryFactory.HistoryViewHolder) {
            int dragFlags = 0;//только СВАЙП, поэтому 0
            int swipeFlags = ItemTouchHelper.START;  //| ItemTouchHelper.END;  - START - вправо   LEFT - влево
            return makeMovementFlags(dragFlags, swipeFlags);
        }
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (direction == 16) {
            mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
        }
    }
}
