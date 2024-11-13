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
            <refund-table
            v-if="refunds && refunds.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:refunds="refunds"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-refunds="getAllRefunds"
             >

            </refund-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RefundTable from "@/components/RefundTable";
import RefundService from "../services/RefundService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RefundTable,
  },
  data() {
    return {
      refunds: [],
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
    async getAllRefunds(sortBy='refundId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RefundService.getAllRefunds(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.refunds.length) {
					this.refunds = response.data.refunds;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching refunds:", error);
        }
        
      } catch (error) {
        console.error("Error fetching refund details:", error);
      }
    },
  },
  mounted() {
    this.getAllRefunds();
  },
  created() {
    this.$root.$on('searchQueryForRefundsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRefunds();
    })
  }
};
</script>
<style></style>
