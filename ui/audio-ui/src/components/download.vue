<template>
  <div class="download-container">
    <h1>文件下载</h1>
    <button @click="fetchAndDownload">点击生成并下载文件</button>
    <div v-if="success" class="download-section">
      <p>文件已生成: {{ fileName }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      success: false, // 是否成功获取文件路径
      filePath: "", // 文件路径
      contentDisposition: "", // 文件下载名
      fileName: "", // 文件名
    };
  },
  methods: {
    async fetchFileInfo() {
      try {
        // 调用后端接口获取文件信息
        const response = await fetch("http://localhost:8085/audio/download/single?filename=94cf529b-bf83-468d-867f-22dde9e533bb.ogg", {
          method: "GET", // 假设后端需要 POST 请求
          headers: {
            "Content-Type": "application/json",
          },
        });

        // 确保响应是成功的
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        console.log(result);

        if (result.success) {
          this.filePath = result.data.filePath;
          this.contentDisposition = result.data.contentDisposition;
          this.fileName = this.contentDisposition
            .split("filename=")[1]
            .replace(/"/g, "");
          this.success = true;
        } else {
          console.error("获取文件信息失败:", result.message);
        }
      } catch (error) {
        console.error("获取文件信息出错:", error);
      }
    },
    downloadFile() {
      if (!this.filePath || !this.fileName) {
        console.error("文件路径或文件名无效");
        return;
      }
      // 创建一个隐藏的 <a> 元素用于下载
      const link = document.createElement("a");
      link.href = this.filePath; // 后端返回的文件路径
      link.download = this.fileName; // 从 Content-Disposition 获取的文件名
      document.body.appendChild(link); // 必须将元素添加到 DOM
      link.click();
      document.body.removeChild(link); // 清理 DOM
    },
    async fetchAndDownload() {
      await this.fetchFileInfo(); // 先获取文件信息
      if (this.success) {
        this.downloadFile(); // 如果成功，执行下载
      }
    },
  },
};
</script>

<style scoped>
.download-container {
  text-align: center;
  margin-top: 50px;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}

.download-section {
  margin-top: 20px;
}
</style>
