<script lang="ts">
  import TagList from "./elements/TagList.svelte";

  export let name: string;
  export let date: string;
  export let tags: string[];
</script>

<div class="main">
  <h3>{name}</h3>
  <span class="attr">Data: {date}</span>
  <TagList tags={tags} />
</div>

<style>
  .main{
    display: flex;
    flex-direction: column;
    background: var(--light);
    color: var(--on-light);
    border-radius: 10px;
    text-align: left;

    min-width: 250px;
    flex-basis: 0;
    cursor: pointer;
  }
  
  h3{
    color: white;
    background-color: var(--dark);
    margin: 0;
    border-radius: 10px 10px 0 0;
    padding: 16px;
  }

  .attr {
    padding: 4px;
  }
</style>
