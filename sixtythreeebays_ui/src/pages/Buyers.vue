<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <buyer-table
            v-if="buyers && buyers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:buyers="buyers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-buyers="getAllBuyers"
             >

            </buyer-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BuyerTable from "@/components/BuyerTable";
import BuyerService from "../services/BuyerService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BuyerTable,
  },
  data() {
    return {
      buyers: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllBuyers(sortBy='buyerId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BuyerService.getAllBuyers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.buyers.length) {
					this.buyers = response.data.buyers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching buyers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching buyer details:", error);
      }
    },
  },
  mounted() {
    this.getAllBuyers();
  },
  created() {
    this.$root.$on('searchQueryForBuyersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBuyers();
    })
  }
};
</script>
<style></style>
