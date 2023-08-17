<!--작성자:황용학-->


<template>
  <div class="d-flex">
    <div class="cscView">
      <CscCategory :currentTab="'inquiry'"></CscCategory>
    </div>
    <div id="cscMain">
      <div>
        <div>
          <h2>자주 묻는 질문 </h2>
          <div id="cscBoradSearch">
            <div @submit="inquirysearch()" class="searchForm">
              <font-awesome-icon id="csc-search-icon" :icon="['fas', 'magnifying-glass']" />
              <input class="cscviewkeyword" v-model="form.keyword" @keyup.enter="inquirysearch">
              <b-button class="bedu-bg-custom-blue csc-writepath-btn" id="csc-writepath-btn1" @click="inquirysearch">
                검색
              </b-button>
            </div>
          </div>
        </div>
      </div>
      <table id="cscboard-table">
        <thead>
          <tr id="faq-head">
            <th>번호</th>
            <th>제목</th>            
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in boxItems" :key="index" :class="{ 'expanded': item.isExpanded }"
            @click="toggleBox(index)">
            <td>
              {{ item.number }}
            </td>
            <td>
              <div class="">{{ item.question }}</div>
              <div class="faq-answer" v-if="item.isExpanded">{{ item.answer }}</div>
            </td>
          </tr>
        </tbody>
      </table>
      <!-- 검색 결과가 없을 때-->
      <p v-if="itemlist.length === 0 && form.keyword.trim() !== ''" id="searched">검색되는 결과가 없습니다.</p>
      
      <!--페이징 진행-->
      <div class="pagination-container">
        <b-pagination v-model="currentPage" :total-rows="itemlist.length" :per-page="pageSize"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script>

import CscCategory from '@/components/CscCategory.vue';
//import InquiryDetail from '@/Notice/InquiryDetail.vue';

import '@/assets/css/CscViewStyle.css';
export default {
  name: 'itemlist',

  data() {
    return {
      boxItems: [
        { number: 1, question: '분야별 강의/취업 로드맵, 토픽, 챕터, 레슨 단위에 대해 알고 싶어요.', answer: 'BEDU 강의 구조는 다음과 같이 이루어져 있어요. 레슨(강의/노트/퀴즈/과제) < 챕터 < 토픽 < 분야별 강의/취업 로드맵 레슨은 동영상 강의, 노트, 퀴즈, 과제를 나타내는 가장 작은 단위의 수업이에요. 이를 묶어 놓은 것이 챕터, 그리고 챕터를 묶어 수강할 수 있도록 만든 것이 토픽입니다. 분야별 강의/취업 로드맵은 이 모든 과정을 가장 효과적으로 학습할 수 있도록 단계별로 배치한 것입니다.', isExpanded: false },
        { number: 2, question: '동영상 강의는 몇 분 정도인가요?', answer: 'BEDU 강의는 5분 내외의 짧은 영상으로 이루져있어요. 예상 소요시간은 각 토픽 페이지에서 확인하실 수 있습니다.', isExpanded: false },
        { number: 3, question: '강의를 듣다가 궁금증이 생기면 어떻게 해야 하나요?', answer: '레슨 하단 질문하기를 통해 커뮤니티에 궁금증을 남기고, 다른 수강생들과 함께 해결해보세요. 커뮤니티 정책을 참고하여 커뮤니티를 200% 활용해 보세요.', isExpanded: false },
        { number: 4, question: '강의 영상을 다운로드할 수 있나요?', answer: '아니요. 강의 내용은 다운로드가 불가능합니다. 다만 노트북, 태플릿, 스마트 폰 등 다양한 기기에서 언제든지 코드잇 강의를 수강하실 수 있습니다.', isExpanded: false }

      ],

      itemlist: [
      ],

      form: {
        keyword: '',
      },
      sortOption: "default", // 정렬 옵션
      currentPage: 1,
    };
  },

  components: {
    CscCategory
  },

  mounted() {
  },

  computed: {
    paginatedInquiryList() {

      // 현재 페이지에 해당하는 데이터의 시작과 끝 인덱스를 계산합니다.
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;

      // 현재 페이지에 해당하는 데이터를 추출하여 반환합니다.
      return this.itemlist.slice(startIndex, endIndex);
    },

  },

  methods: {

    toggleBox(index) {
      this.boxItems[index].isExpanded = !this.boxItems[index].isExpanded;
    },

    inquirysearch() {

      const keyword = this.form.keyword.trim(); // 입력된 검색어를 양쪽 공백을 제거하여 가져옵니다.

      if (!keyword) {
        alert("검색어를 입력해주세요!");
        return;
      }

      this.$axiosSend('get', '/api/inquiry/inquirySerach', { keyword })
        .then((response) => {
          const dataFromBackend = response.data;
          console.log("Response from Backend:", dataFromBackend);
          this.inquirylist = response.data;
        })
        .catch((error) => {
          alert("검색되는 결과가 없습니다.");
          console.error("Error:", error);
        });
    },
/*     itemList() {
      this.$axiosSend('post', '/api/inquiry/inquiryList',)
        .then(res => {
          console.log(res)
          this.inquirylist = res.data;

          // 페이징 처리를 위한 변수들을 설정합니다.
          this.pageSize = 15; // 한 페이지에 보여줄 게시물 수를 설정합니다.
          this.totalPages = Math.ceil(this.inquirylist.length / this.pageSize); // 총 페이지 수를 계산합니다.
          this.currentPage = 1; // 현재 페이지 번호를 초기화합니다.

          // 페이징 처리된 데이터를 가져옵니다.
          this.paginatedInquiryList = this.inquirylist.slice(0, this.pageSize);

        })
        .catch(error => {
          alert(error);
        });
    }, */

  },
};
</script>