package org.oneship.mustattend;

import android.view.View;

// Listener : 이벤트처리
public interface OnStoreItemClickListener {

    // 아이템이 클릭되면 이 함수 호출
    public void onItemClick(AllStoreAdapter.ViewHolder holder, View view, int position);
}
