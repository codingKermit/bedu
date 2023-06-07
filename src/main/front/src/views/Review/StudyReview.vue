<template>
    <div class="review-list">
        <div class="review-group">
            <div class="m-select">
                <ul class="mk-c-tab col8 tab-event">
                    <li><a class="review-a" href="#">전체</a></li>
                    <li><a class="review-a" href="#">기초강의</a></li>
                    <li><a class="review-a" href="#">데이터분석</a></li>
                    <li><a class="review-a" href="#">웹 개발</a></li>
                    <li><a class="review-a" href="#">프로그래밍 언어</a></li>
                    <li><a class="review-a" href="#">인공지능</a></li>
                    <li><a class="review-a" href="#">프로그래밍 교양</a></li>
                    <li><a class="review-a" href="#">개발도구</a></li>
                    <li><a class="review-a" href="#">컴퓨터 공학 전공 지식</a></li>
                    <li><a class="review-a" href="#">디자인</a></li>
                </ul>
            </div>
            <div class="review-search">
                <p class="fw-bold fs-3 text-start" style="margin-top: 20px">수강후기</p>
                <div class="review-search-bar">
                    <div class="review-search-input">
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
        <div class="scroll-container" style="padding: 25px;">
            <table class="review-table" style="margin-bottom:100px;">
                <thead>
                    <tr>
                        <th>강좌</th>
                        <th style="padding-right: 40px">수강후기</th>
                        <th>별점</th>
                        <th>작성자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(reviews, index) in fetchedReviews" :key="index">
                        <td class="review-title">{{ reviews.title }}</td>
                        <td>
                            <span class="review-content" style="padding-right: 40px">{{ reviews.content }}</span>
                        </td>
                        <td>
                            <div class="review-star-rating">
                                <span class="review-star" v-for="star in reviews.star" :key="star"
                                >&#9733;</span
                                >
                            </div>
                        </td>
                        <td>{{ reviews.writer }}</td>
                    </tr>
                    <infinite-loading @infinite="fetchReviews" style= "padding: 50px;">
                        <template #no-more>마지막 후기 입니다.</template>
                    </infinite-loading>
                </tbody>
                <tr v-if="isLoading">
                    <td colspan="5" class="loading-text">로딩 중...</td>
                </tr>
                <div class="infinite-loading-container">
                </div>
            </table>
        </div>
    </div>
</template>

<script>
    import InfiniteLoading from "infinite-loading-vue3-ts";
    import '@/assets/css/reviewStyle.css';

    export default {
        components: {
            InfiniteLoading,
        },
        data() {
            return {
                searchKeyword: "", // 검색어
                fetchedReviews: [], // 검색 전에 모든 후기를 저장하는 배열
                searchedReviews: [], // 검색 결과를 저장하는 배열
                sortOption: "default", // 정렬 옵션
                content: "",
                isLoading: false, // 로딩 중 여부
                currentPage: 1, // 현재 페이지 번호
                itemsPerPage: 20, // 한 페이지에 보여줄 아이템 수
                totalItems: 0, // 총 아이템 수
            };
        },
        methods: {
            // 후기 가져오기
            fetchReviews($state) {
                this.isLoading = true;
                this.$axiosSend("get", "/api/reviews", {
                    page: this.currentPage, 
                    size: this.itemsPerPage,
                })
                .then((response) => {
                    const {  totalElements } = response.data;
                    if (response.data.length) {
                         // 가져온 후기를 fetchedReviews 배열에 추가
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
            //별점순, 최신순 정렬
            sortReviews() {
                switch (this.sortOption) {
                    case "highRating":
                        // 별점 높은 순으로 후기 정렬
                        this.fetchedReviews = [...this.fetchedReviews].sort(
                            (a, b) => b.star - a.star
                        );
                    break;
                    case "lowRating":
                        // 별점 낮은 순으로 후기 정렬
                        this.fetchedReviews = [...this.fetchedReviews].sort(
                            (a, b) => a.star - b.star
                        );
                    break;
                    default:
                        // 최신 순으로 후기 정렬
                        this.fetchedReviews = [...this.fetchedReviews].sort(
                            (a, b) => new Date(b.reviewDate) - new Date(a.reviewDate)
                        );
                    break;
                }
            },
            // 후기 검색
            searchReviews() {
                const keyword = this.searchKeyword
                    ? this.searchKeyword.toLowerCase()
                    : "";

                if (Array.isArray(this.fetchedReviews)) {
                    // fetchedReviews 배열에서 검색어와 일치하는 후기 필터링
                    const filteredReviews = this.fetchedReviews.filter((review) => {
                        return (
                            (review.title && review.title.toLowerCase().includes(keyword)) ||
                            (review.content &&
                            review.content.toLowerCase().includes(keyword)) ||
                            (review.writer && review.writer.toLowerCase().includes(keyword))
                        );
                    });

                    this.searchedReviews = filteredReviews; // 검색 결과를 searchedReviews 배열에 저장
                    this.sortReviews(); // 검색 후 정렬을 수행하도록 수정
                }
            },
        },
        mounted() {

        },
    };
</script>
