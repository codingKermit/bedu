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
        <p class="fw-bold fs-3 text-start" style="margin-top: 20px">수강후기</p>
        <div class="search-bar">
          <div class="search-input">
            <input
              type="text"
              placeholder="검색어를 입력하세요"
              v-model="searchKeyword"
              @keyup.enter="searchReviews"
            />
            <button @click="searchReviews">검색</button>
          </div>
        </div>
      </div>
      <div class="review-sort" style="float: right">
        <select v-model="sortOption" @change="sortReviews" style="border: none">
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
            <th style="padding-right: 10%">강좌</th>
            <th style="padding-right: 20%">수강후기</th>
            <th>별점</th>
            <th>작성자</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(reviews, index) in fetchedReviews" :key="index">
            <td>{{ index + 1 }}</td>
            <td style="padding-right: 10%">{{ reviews.title }}</td>
            <td>
              <span class="review-content">{{ reviews.content }}</span>
            </td>
            <td>
              <div class="star-rating">
                <span class="star" v-for="star in reviews.star" :key="star"
                  >&#9733;</span
                >
              </div>
            </td>
            <td style="padding-right: 20px">{{ reviews.writer }}</td>
          </tr>
          <infinite-loading @infinite="fetchReviews">
            <template #no-more>마지막 후기 입니다.</template>
          </infinite-loading>
        </tbody>
        <tr v-if="isLoading">
          <td colspan="5" class="loading-text">로딩 중...</td>
        </tr>
        <div class="infinite-loading-container">
          <!-- <infinite-loading @infinite="fetchReviews">
            <template #no-more>마지막 후기 입니다.</template>
          </infinite-loading> -->
        </div>
      </table>
    </div>
  </div>
</template>

<script>
import InfiniteLoading from "infinite-loading-vue3-ts";

export default {
  components: {
    InfiniteLoading,
  },
  data() {
    return {
      searchKeyword: "",
      fetchedReviews: [],
      searchedReviews: [],
      sortOption: "default",
      content: "",
      isLoading: false,
      currentPage: 1,
      itemsPerPage: 20,
      totalItems: 0,
    };
  },
  methods: {
    fetchReviews($state) {
      this.isLoading = true;
      console.log("page : " + this.currentPage)
      this.$axiosSend("get", "/api/reviews", {
        page: this.currentPage, 
        size: this.itemsPerPage,
      })
        .then((response) => {
          const {  totalElements } = response.data;
          if (response.data.length) {
            this.fetchedReviews.push(...response.data);
            this.currentPage++;
            $state.loaded();
          } 
          else {
            $state.complete();
          }
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
    sortReviews() {
      switch (this.sortOption) {
        case "highRating":
          this.fetchedReviews = [...this.fetchedReviews].sort(
            (a, b) => b.star - a.star
          );
          break;
        case "lowRating":
          this.fetchedReviews = [...this.fetchedReviews].sort(
            (a, b) => a.star - b.star
          );
          break;
        default:
          this.fetchedReviews = [...this.fetchedReviews].sort(
            (a, b) => new Date(b.reviewDate) - new Date(a.reviewDate) // reviewDate 필드로 수정
          );
          break;
      }
    },
    searchReviews() {
      const keyword = this.searchKeyword
        ? this.searchKeyword.toLowerCase()
        : "";

      if (Array.isArray(this.fetchedReviews)) {
        const filteredReviews = this.fetchedReviews.filter((review) => {
          return (
            (review.title && review.title.toLowerCase().includes(keyword)) ||
            (review.content &&
              review.content.toLowerCase().includes(keyword)) ||
            (review.writer && review.writer.toLowerCase().includes(keyword))
          );
        });

        this.searchedReviews = filteredReviews;
        this.sortReviews(); // 검색 후 정렬을 수행하도록 수정
      }
    },
  },
  mounted() {

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

.mk-c-tab > li {
  display: inline-block;
  width: 20%;
  text-align: center;
}

.mk-c-tab > li > a {
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
</style>