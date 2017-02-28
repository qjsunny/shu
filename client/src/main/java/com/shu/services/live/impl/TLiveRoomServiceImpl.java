package com.shu.services.live.impl;

import com.shu.db.dao.live.TLiveRoomMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.live.TLiveRoom;
import com.shu.services.live.TLiveRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */
@Service("lRoomService")
@Transactional
public class TLiveRoomServiceImpl implements TLiveRoomService {

    @Autowired
    TLiveRoomMapper tLiveRoomMapper;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<TLiveRoom> getLRoomListByParam(TLiveRoom follow, Order order, Pager page) {
        if (page != null) {
            int count = tLiveRoomMapper.selectCountByParam(follow);
            page.setRecordCount(count);
        }
        return tLiveRoomMapper.selectListByParam(follow, order, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addLRoom(TLiveRoom follow) {
        tLiveRoomMapper.insertSelective(follow);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyLRoom(TLiveRoom follow) {
        tLiveRoomMapper.updateByPrimaryKeySelective(follow);
    }

    @Override
    @Transactional(readOnly = true)
    public TLiveRoom getLRoomById(String id) {
        return tLiveRoomMapper.selectByPrimaryKey(id);
    }
}
