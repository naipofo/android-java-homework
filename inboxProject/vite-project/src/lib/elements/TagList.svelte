<script lang="ts">
  export let tags: string[];
</script>

<div class="tags">
  {#each tags as tag}
    <span class="tag">{tag}</span>
  {/each}
</div>

<style>
  .tags{
    padding: 4px;
  }
  .tags {
    display: flex;
  }
  .tags > span {
    padding-right: 8px;
  }
  .tags > span:not(:last-child)::after{
    content: ", ";
  }
</style>
