package com.zzu.cui.iotek.biz;

import com.zzu.cui.entity.User;

public interface UserBiz {
	
	//�û���¼�����ص�¼��ȥ����Ϣ
	public User login(User user);
	//ע���û�
	public int registerUser(User user);

}
