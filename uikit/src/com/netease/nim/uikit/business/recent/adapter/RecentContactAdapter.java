package com.netease.nim.uikit.business.recent.adapter;

import android.support.v7.widget.RecyclerView;

import com.netease.nim.uikit.R;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.business.recent.RecentContactsCallback;
import com.netease.nim.uikit.business.recent.holder.CommonRecentViewHolder;
import com.netease.nim.uikit.business.recent.holder.TeamRecentViewHolder;
import com.netease.nim.uikit.common.CommonUtil;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemQuickAdapter;
import com.netease.nim.uikit.common.ui.recyclerview.holder.BaseViewHolder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.util.List;


/**
 * Created by huangjun on 2016/12/11.
 */

public class RecentContactAdapter extends BaseMultiItemQuickAdapter<RecentContact, BaseViewHolder>{

    interface ViewType {
        int VIEW_TYPE_COMMON = 1;
        int VIEW_TYPE_TEAM = 2;
    }

    private RecentContactsCallback callback;

    public RecentContactAdapter(RecyclerView recyclerView, List<RecentContact> data) {
        super(recyclerView, data);
        addItemType(ViewType.VIEW_TYPE_COMMON, R.layout.nim_recent_contact_list_item, CommonRecentViewHolder.class);
        addItemType(ViewType.VIEW_TYPE_TEAM, R.layout.nim_recent_contact_list_item, TeamRecentViewHolder.class);
    }

    @Override
    protected int getViewType(RecentContact item) {
        return item.getSessionType() == SessionTypeEnum.Team ? ViewType.VIEW_TYPE_TEAM : ViewType.VIEW_TYPE_COMMON;
    }

    @Override
    protected void convert(BaseViewHolder baseHolder, RecentContact item, int position, boolean isScrolling) {
        super.convert(baseHolder, item, position, isScrolling);
        baseHolder.addOnClickListener(R.id.delete);
        if (CommonUtil.role == CommonUtil.SELLER) {
            if (item.getSessionType() == SessionTypeEnum.P2P) {
                if (CommonUtil.classbroRobot.equals(item.getContactId()) || CommonUtil.systemNotify.equals(item.getContactId())
                        || item.getContactId().startsWith("stud")|| NimUIKit.getAccount().toLowerCase().equals(item.getContactId().toLowerCase())) {
                    baseHolder.setText(R.id.delete,"删除");
                } else {
                    baseHolder.setText(R.id.delete, "结束咨询");
                }
            } else {
                baseHolder.setText(R.id.delete,"删除");
            }
        }
    }

    @Override
    protected String getItemKey(RecentContact item) {
        StringBuilder sb = new StringBuilder();
        sb.append(item.getSessionType().getValue()).append("_").append(item.getContactId());
        return sb.toString();
    }

    public RecentContactsCallback getCallback() {
        return callback;
    }

    public void setCallback(RecentContactsCallback callback) {
        this.callback = callback;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
