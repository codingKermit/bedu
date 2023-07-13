<template>
  <div class="d-flex">
    <div class="cscView" id="cscView">
      <CscCategory :currentTab="'inquiry'"></CscCategory>
    </div>
    <div id="cscMain">
      <div>
        <div>
          <div class="cscBoradSearch" id="cscBoradSearch">
            <div @submit="qnasearch()" class="searchForm">
              <font-awesome-icon id="csc-search-icon" :icon="['fas', 'magnifying-glass']" />
              <input class="cscviewkeyword" v-model="form.keyword" ref="keyword" @keyup.enter="qnasearch">
              <b-button class="bedu-bg-custom-blue csc-writepath-btn" id="csc-writepath-btn" @click="goToInquiryPage">
                <font-awesome-icon :icon="['fas', 'pencil']" />
                문의하기
              </b-button>
            </div>
          </div>
        </div>
        <h2>1대1 문의 </h2>
        <div class="selectBox">
          <select id="cscSortOption" v-model="sortOption" @change="sortReviews">
            <option value="default">최신순</option>
            <option value="highViews">????</option>
          </select>
        </div>
      </div>
      <table class="w3-table-all" id="cscboard-table">
        <thead>
          <tr>
            <th id="cscTitle">제목</th>
            <th>작성자</th>
            <th>작성일자</th>
            <th>비밀글</th>
          </tr>
        </thead>
        <tbody>
          <tr :key="index" v-for="(inquiry, index) in inquirylist">
            <td id="cscboard-table-tds">
              <b-link class="text-start text-body" :to="{
                name:'inquiryDetail',
                query : { //index.js 라우터에서 봤을 때 inquiryDetail문은 query 문으로 보내야 함
                  vocNum : inquiry.vocNum
                }
              }">
                {{ inquiry.title }}
              </b-link>
            </td>
            <td>
              {{ inquiry.userName }}</td>
            <td>{{ formatDateTime(inquiry.regDate)}}</td>
            <td>
              <font-awesome-icon :icon="['fas', 'lock']" /> 
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
 
import CscCategory from '@/components/CscCategory.vue';
import '@/assets/css/CscViewStyle.css';
export default {
  name: 'inquirylist',

  data() {
    return {
      inquirylist: [
      ],
      form: {
        keyword: '',
      },
      sortOption: "default", // 정렬 옵션
      totalItems: 0,
      totalPage: 0,
      currentPage: 1
    }; 

  },

  created() {
    this.qnasearch();
  },

  components: {
    CscCategory
  },

  mounted() {
  },

  methods: {
    goToInquiryPage() {
      window.location.href = "/inquiry"; // 원하는 문의 페이지의 URL로 변경해주세요
    },
      
    sortReviews() {
      if (this.sortOption === "default") {
        // 최신 순으로 정렬
        this.inquirylist.sort((a, b) => {
          return new Date(b.qna_date) - new Date(a.qna_date);
        });
      } else if (this.sortOption === "highViews") {
        // 조회수 순으로 정렬
        this.inquirylist.sort((a, b) => {
          return b.qna_cnt - a.qna_cnt;
        });
      }
    },

    qnasearch() {
      this.$axiosSend('post', '/api/inquiry/inquiryList')
        .then(res => {
          console.log(res)
          this.inquirylist = res.data;
          // this.sortReviews(); // 정렬 수행
        })
        .catch(error => {
          alert(error);
        });
    },

    infiniteHandler($state) {
      console.log('번호:', this.currentPage);
      this.$axiosSend('get', '/api/inquiry/inquiryList', {
        page: this.currentPage,
      })
        .then(res => {
          if (res.data.length) {
            this.currentPage++;
            this.qnalist.push(...res.data);

            console.log('리스', this.inquirylist);
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch(err => {
          console.log(err);
        })
    },
    formatDateTime(value) {
                // value는 날짜 값입니다
                const now = new Date();
                const date = new Date(value);

                const diffInMilliseconds = now - date;
                const diffInSeconds = Math.floor(diffInMilliseconds / 1000);
                const diffInMinutes = Math.floor(diffInSeconds / 60);
                const diffInHours = Math.floor(diffInMinutes / 60);
                const diffInDays = Math.floor(diffInHours / 24);

                if (diffInDays > 0) {
                    return `${diffInDays}일 전`;
                } else if (diffInHours > 0) {
                     return `${diffInHours}시간 전`;
                } else if (diffInMinutes > 0) {
                    return `${diffInMinutes}분 전`;
                } else {
                    return '방금 전';
                }
            },
     },  
};
</script>