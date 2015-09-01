package billManager.service;

import providerManager.service.ProviderService;
import billManager.vo.Bill;

/**
 * 
 * @description У���˵��ĺϷ���
 *
 * @author hello world
 *
 */
public class BillCheck {
	/**
	 * У��Bill�ĺϷ���
	 * @param bill
	 * @return
	 */
	public boolean checkBill(Bill bill ) throws Exception {
		/*private int billId ;
		private String provider ;
		private double price ;
		private String priceUnit ;
		private int goodsNum ;
		private boolean isPay ;
		private String goodsName ;
		private String goodsDesc ;
		private Timestamp billTime ;*/
		return  checkProviderId(bill.getProviderId()) ;
	}
	/**
	 * ����ID���Provider�Ƿ����
	 * @param provider
	 * @return
	 */
	public boolean checkProviderId(double providerId ) throws Exception {
		return new ProviderService().findProviderById(providerId) == null ? false : true ;
	}
}
