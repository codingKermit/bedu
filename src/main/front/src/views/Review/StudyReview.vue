<template>
  <div class="review-list">
    <div class="group">
      <div class="m-select">
        <ul class="mk-c-tab col8 tab-event">
          <li><a href="#">전체</a></li>
          <li><a href="#">기초강의</a></li>         
          <li><a href="#">데이터분석</a></li>
          <li><a href="#">웹 개발</a></li>
          <li><a href="#">프로그래밍 언어</a></li>
          <li><a href="#">인공지능</a></li>
          <li><a href="#">프로그래밍 교양</a></li>
          <li><a href="#">개발도구</a></li>
          <li><a href="#">컴퓨터 공학 전공 지식</a></li>
          <li><a href="#">디자인</a></li>
        </ul>
      </div>
      <div class="review-search">
        <p class="fw-bold fs-3 text-start" style="margin-top:20px">수강후기</p>
        <div class="search-bar">
          <div class="search-input">
            <input type="text" placeholder="검색어를 입력하세요" v-model="searchKeyword" @keyup.enter="searchReviews">
            <button @click="searchReviews">검색</button>
          </div>
        </div>
      </div>
      <div class="review-sort" style="float: right;">
      <select v-model="sortOption" @change="sortReviews" style="border: none;">
        <option value="default">최신 순</option>
        <option value="highRating">평점 높은 순</option>
        <option value="lowRating">평점 낮은 순</option>
      </select>
    </div>
    </div>
    <div class="scroll-container">
      <table class="review-table">
        <thead>
          <tr>
            <th>번호</th>
            <th style="padding-right: 10%;">강좌</th>
            <th style="padding-right: 20%;">수강후기</th>
            <th>별점</th>
            <th>작성자</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(review, index) in searchedReviews" :key="index">
            <td>{{ index + 1 }}</td>
            <td style="padding-right: 10%;">{{ review.title }}</td>
            <td>
              <span class="review-content">{{ review.content }}</span>
            </td>
            <td>
              <div class="star-rating">
                <span class="star" v-for="star in review.star" :key="star">&#9733;</span>
              </div>
            </td>
            <td style="padding-right: 20px;">{{ review.writer }}</td>
          </tr>
        </tbody>
        <tr v-if="isLoading">
          <td colspan="5" class="loading-text">
            로딩 중...
          </td>
        </tr>
      </table>
      <div class="infinite-loading-container">
        <infinite-loading @infinite="fetchMoreReviews">
          <template #no-more>마지막 후기 입니다.</template>
        </infinite-loading>
      </div>
      <button class="top-button" @click="scrollToTop" v-show="shouldShowTopButton">
        Top
      </button>
    </div>
  </div>
</template>

<script>
import InfiniteLoading from 'infinite-loading-vue3-ts';

export default {
  props: {
    reviews: {
      type: Array,
      required: true,
    },
  },
  components: {
    InfiniteLoading,
  },
  data() {
    return {
      searchKeyword: '',
      fetchedReviews: [],
      searchedReviews: [],
      sortOption: 'default',
      content: '',
      isLoading: false,
      currentPage: 0,
      itemsPerPage: 20,
      totalItems: 0,
      shouldShowTopButton: false,
    };
  },
  methods: {
    fetchReviews() {
  this.isLoading = true;
  this.$axiosSend('get', '/api/reviews', { 
            page: this.currentPage,
            size: this.itemsPerPage, 
    })
    .then((response) => {
      const { content, totalElements } = response.data;
      this.fetchedReviews = content;
      this.totalItems = totalElements;
      this.isLoading = false;

      this.searchReviews(); // 검색 수행
      this.sortReviews(); // 정렬 수행
    })
    .catch((error) => {
      console.error(error);
      this.isLoading = false;
    });
},
fetchMoreReviews($state) {
  this.isLoading = true;
  const nextPage = this.currentPage + 1;
  this.$axiosSend('get', '/api/reviews/more', { 
            page: this.nextPage,
            size: this.itemsPerPage, 
      })
      .then((response) => {
        const { content } = response.data;
        this.fetchedReviews = [...this.fetchedReviews, ...content];
        this.currentPage = nextPage;
        this.isLoading = false;

        this.searchReviews(); // 검색 수행
        this.sortReviews(); // 정렬 수행

        $state.loaded(); // 추가 리뷰 로딩 완료
        if (content.length < this.itemsPerPage) {
          $state.complete(); // 추가 리뷰 없음
        }
      })
        .catch((error) => {
          console.error(error);
          this.isLoading = false;
          $state.complete(); // 추가 리뷰 로딩 실패
        });
    },
sortReviews() {
  switch (this.sortOption) {
    case 'highRating':
      this.searchedReviews = [...this.searchedReviews].sort((a, b) => b.star - a.star);
      break;
    case 'lowRating':
      this.searchedReviews = [...this.searchedReviews].sort((a, b) => a.star - b.star);
      break;
    default:
      this.searchedReviews = [...this.searchedReviews].sort(
        (a, b) => new Date(b.createdAt) - new Date(a.createdAt)
      );
      break;
  }
},
searchReviews() {
  const keyword = this.searchKeyword ? this.searchKeyword.toLowerCase() : '';
  const filteredReviews = this.fetchedReviews.filter((review) => {
    return (
      review.title.toLowerCase().includes(keyword) ||
      review.content.toLowerCase().includes(keyword) ||
      review.writer.toLowerCase().includes(keyword)
    );
  });

  this.searchedReviews = filteredReviews;
  this.sortReviews(); // 검색 후 정렬을 수행하도록 수정
},
    handleScroll() {
      console.log('Scroll event occurred');
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
      this.shouldShowTopButton = scrollTop > 100;
    },
    scrollToTop() {
      window.scrollTo({
        top: 0,
        behavior: 'smooth',
      });
    },
  },
  mounted() {
  this.fetchReviews();
  window.addEventListener('scroll', this.handleScroll);
},
  beforeUnmount() {
    window.removeEventListener('scroll', this.handleScroll);
  },
};
</script>

<style scoped>
.group {
  padding: 25px;
}

.mk-c-tab.col8 {
  border: 1px solid #eee;
  border-bottom: 0;
  border-right: 0;
  padding: 0;
}

.mk-c-tab {
  text-align: center;
  clear: both;
  overflow: hidden;
  margin-bottom: 20px;
}

.mk-c-tab>li {
  display: inline-block;
  width: 20%;
  text-align: center;
}

.mk-c-tab>li>a {
  display: block;
  padding: 12px 10px;
  background: #f8f8f8;
  border-right: 1px solid #eee;
  font-weight: 300;
  color: inherit;
}

.review-search {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.search-bar {
  display: flex;
  align-items: center;
}

.search-input input {
  width: 200px;
  padding: 5px;
  margin-right: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.search-input button {
  padding: 5px 10px;
  background-color: #f8f8f8;
  border: 1px solid #ccc;
  border-radius: 4px;
  color: inherit;
  cursor: pointer;
}

a {
  text-decoration: none;
  cursor: pointer;
  color: inherit;
  font-size: inherit;
  font-weight: inherit;
}

.review-table {
  width: 100%;
  border-collapse: collapse;
}

.review-table th,
.review-table td {
  padding: 10px;
  text-align: center;
  border-bottom: 1px solid #ccc;
}

.star-rating {
  display: flex;
  justify-content: center;
  align-items: center;
}

.star-rating .star {
  color: gold;
}

.review-table .review-writer {
  display: block;
  width: 200px; 
  white-space: nowrap;
  text-overflow: ellipsis;
  word-break: break-all;
  overflow-wrap: break-word;
}
.review-write-button-container {
  display: flex;
  justify-content: flex-end;
  margin: 10px;
  padding: 10px;
}

.review-write-button {
  width: 150px;
  height: 50px;
  background-color: #f8f8f8;
  border: 1px solid #ccc;
}
.scroll-container {
  position: relative;
}

.infinite-loading-container {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  text-align: center;
}

.top-button {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 9999;
  display: none;
  padding: 10px 20px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.top-button:hover {
  background-color: #555;
}

</style>