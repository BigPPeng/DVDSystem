package com.zzu.cui.entity;

//定义的这个类通过光盘来查询某光盘的使用情况
	public class Record {
		private int id;//记录的序号ID
		private int uid;//用户id
		private int did;//光盘ID
		private String lendTime;//光盘借出时间
		private String returnTime;//光盘归还时间
		
		public Record() {
			super();
		}
		
		public Record(int uid, int did, String lendTime, String returnTime) {
			super();
			this.uid = uid;
			this.did = did;
			this.lendTime = lendTime;
			this.returnTime = returnTime;
		}
		public Record(int id, int uid, int did, String lendTime, String returnTime) {
			super();
			this.id = id;
			this.uid = uid;
			this.did = did;
			this.lendTime = lendTime;
			this.returnTime = returnTime;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getUid() {
			return uid;
		}
		public void setUid(int uid) {
			this.uid = uid;
		}
		public int getDid() {
			return did;
		}
		public void setDid(int did) {
			this.did = did;
		}
		public String getLendTime() {
			return lendTime;
		}
		public void setLendTime(String lendTime) {
			this.lendTime = lendTime;
		}
		public String getReturnTime() {
			return returnTime;
		}
		public void setReturnTime(String returnTime) {
			this.returnTime = returnTime;
		}
		

}
