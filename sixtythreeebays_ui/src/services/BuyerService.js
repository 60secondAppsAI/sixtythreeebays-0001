import http from "../http-common"; 

class BuyerService {
  getAllBuyers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/buyer/buyers`, searchDTO);
  }

  get(buyerId) {
    return this.getRequest(`/buyer/${buyerId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/buyer?field=${matchData}`, null);
  }

  addBuyer(data) {
    return http.post("/buyer/addBuyer", data);
  }

  update(data) {
  	return http.post("/buyer/updateBuyer", data);
  }
  
  uploadImage(data,buyerId) {
  	return http.postForm("/buyer/uploadImage/"+buyerId, data);
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

export default new BuyerService();
