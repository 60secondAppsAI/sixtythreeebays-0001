import http from "../http-common"; 

class RefundService {
  getAllRefunds(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/refund/refunds`, searchDTO);
  }

  get(refundId) {
    return this.getRequest(`/refund/${refundId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/refund?field=${matchData}`, null);
  }

  addRefund(data) {
    return http.post("/refund/addRefund", data);
  }

  update(data) {
  	return http.post("/refund/updateRefund", data);
  }
  
  uploadImage(data,refundId) {
  	return http.postForm("/refund/uploadImage/"+refundId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new RefundService();
