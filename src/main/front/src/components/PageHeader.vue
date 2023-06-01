<!-- PageHeader.vue -->
<template>
  <div id="nav1">
    <router-link to="/">
      <img src="@/assets/imgs/Logo.png" width="150">
    </router-link>
    <div class="dropdown" @mouseenter="openDropdown" @mouseleave="closeDropdown">
      <a class="dropdown-toggle no-arrow" type="button" id="dropdownMenuButton1" :aria-expanded="isDropdownOpen"
        @click="navigateTo('/lectureField')">
        분야별 강의
      </a>
      <ul class="dropdown-menu" v-show="isDropdownOpen" aria-labelledby="dropdownMenuButton1">
        <li v-for="item in categories" :key="item.cate_code">
          <router-link :to='"/lectureCategories/" + item.cate_code + "?cnt_mid_cate=" + item.children[0].cate_code'>
            <a style="cursor: pointer;" class="dropdown-item">{{ item.cate_kor }}</a>
          </router-link>
        </li>
      </ul>
    </div>
    <router-link to="/comm/freBd">커뮤니티</router-link>
    <router-link to="/review">수강후기</router-link>
    <router-link to="/companyStudy">기업교육</router-link>
    <router-link to="/StatisticsMain">통계</router-link>
  </div>
  <div id="nav2">
    <div class="search-popup">
      <div class="border-3 rounded-pill p-2 d-flex align-middle text-center m-auto border-bedu">
        <font-awesome-icon class="m-auto mx-3" :icon="['fas', 'magnifying-glass']" />
        <b-form-input class="border-0 me-2"></b-form-input>
      </div>
    </div>
    <router-link to="/login">로그인</router-link>
    <router-link to="/regist">회원가입</router-link>
  </div>
  <div class="position-fixed d-flex scrollTop rounded-circle">
    <span class="text-white fs-5 position-relative">맨위로</span>
  </div>
</template>

<script>
export default {
  name: 'PageHeader',
  mounted() {
    this.getCategory();
  },

  data() {
    return {
      isDropdownOpen: false,
      categories: []
    };
  },
  methods: {
    categoryChange(item) {
      this.$router.push({
        name: 'course',
        query: {
          category: item.cate_code,
          korCategory: item.cate_kor
        }
      });
    },
    /** 받은 카테고리를 트리 구조로 변경하는 함수 */
    convertToHierarchy(data) {
      const map = {}; // 부모-자식 관계를 저장할 맵
      // 맵에 카테고리 코드를 키로하여 카테고리 객체를 저장
      data.forEach(category => {
        category.children = []; // 자식 카테고리를 저장할 배열 생성
        map[category.cate_code] = category;
      });

      const hierarchy = []; // 최상위 부모 카테고리를 저장할 배열

      // 부모-자식 관계 설정
      data.forEach(category => {
        const parentCode = category.parent_code;
        if (parentCode) {
          const parent = map[parentCode];
          if (parent) parent.children.push(category)
        } else {
          hierarchy.push(category);
        }
        this.categories = hierarchy;
      });
    },
    navigateTo(route) {
      this.$router.push(route);
    },
    openDropdown() {
      this.isDropdownOpen = true;
    },
    closeDropdown() {
      this.isDropdownOpen = false;
    },
    getCategory() {
      let cateData = [];
      /* eslint-disable no-debugger */
      debugger
      this.$axiosSend('get', '/api/getCategory')
        .then((res) => {
          console.log('res::: ', res)
          if (!this.$isEmpty(res?.data)) {
            cateData = res?.data;
          }
          console.log('cateData ::: ', cateData)
          this.convertToHierarchy(cateData)
        })
    },
  },
};
</script>
  
<style scoped>
.scrollTop {
  background-color: var(--black);
  top: 85%;
  right: 6%;
  width: 80px;
  height: 80px;
  align-items: center;
  justify-content: center;
}

.dropdown:hover .dropdown-menu {
  display: block;
}

#nav1 {
  display: flex;
  align-items: center;
}

#nav2 {
  display: flex;
  align-items: center;
}

#nav1 img {
  margin-right: 20px;
}

#nav1 a {
  margin-right: 30px;
  font-family: 'Noto Sans CJK KR', sans-serif;
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}

#nav2 a {
  margin-left: 20px;
  font-family: 'Noto Sans CJK KR', sans-serif;
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}

#nav2 .search-popup {
  align-items: center;
  display: inline-block;
  margin-left: -20px;
  margin-right: -50px;
}

#nav2 .search-popup input {
  width: 100%;
  height: 15px;
  padding: 5px 10px;
  border: none;
  background-color: #F6E0B3;
  border-radius: 30px;
}

.border-bedu {
  background-color: #F6E0B3;
  width: 70%;
  border-color: #EDC268;
}

.no-arrow::after {
  display: none;
}
</style>