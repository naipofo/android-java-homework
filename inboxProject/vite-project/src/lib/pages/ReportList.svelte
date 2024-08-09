<script lang="ts">
  import { createEventDispatcher } from "svelte";
  import Raport from "../Raport.svelte";

  export let projects;

  const dispatch = createEventDispatcher();
  function navigate(id) {
    dispatch("navigate", { id });
  }
  function navAdd() {
    dispatch("add", {});
  }

  let filterQuery: String = "";
  $: allTags = [...new Set(projects.map((e) => e.tags).flat())];
  let activeTag = null;
  function toggleTag(i) {
    activeTag = activeTag == i ? null : i;
  }

  $: filteredList = projects
    .filter((e) => activeTag === null || e.tags.includes(allTags[activeTag]))
    .filter(
      (e) =>
        filterQuery === "" ||
        e.name.toLowerCase().indexOf(filterQuery.toLowerCase()) !== -1
    );
</script>

<section>
  <div class="search">
    <input type="text" bind:value={filterQuery} />
    <div class="tags">
      {#each allTags as tag, i}
        <div on:click={() => toggleTag(i)} class:selected={i === activeTag}>
          {tag}
        </div>
      {/each}
    </div>
    <button on:click={navAdd}>Dodaj nowe</button>
  </div>
  <div class="listcon">
    <div class="list">
      {#each filteredList as { rowid, name, date, tags }}
        <div
          on:click={() => {
            navigate(rowid);
          }}
        >
          <Raport {name} {date} {tags} />
        </div>
      {/each}
    </div>
  </div>
</section>

<style>
  section {
    flex-grow: 1;
    display: flex;
    flex-direction: column;
  }
  .list {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
    gap: 38px;
    padding: 32px;
  }
  .listcon {
    overflow-y: scroll;
    flex-grow: 1;
    flex-basis: 0;
    border: 1px solid gray;
    margin: 32px;
  }
  .listcon::-webkit-scrollbar {
    height: 12px;
    width: 12px;
    background: #f3f0f0;
  }

  .listcon::-webkit-scrollbar-thumb {
    background: var(--dark);
    -webkit-border-radius: 1ex;
  }

  .listcon::-webkit-scrollbar-corner {
    background: #fff;
  }
  .chips {
    display: flex;
  }
  .search {
    margin: 32px;
    margin-bottom: 0;
  }
  .search > input{
    border: 0;
    border-bottom: 2px solid var(--dark);
    width: min(60vw, 400px);
    font-size: 1.7rem;
  }
  .search > input:focus{
    outline: 0;
  }
  .tags {
    display: flex;
    margin: 1rem 0;
  }
  .tags > * {
    margin-right: 0.8rem;
    padding: 0.2rem;
    border-radius: 8px;
    background: var(--light);
    color: var(--on-light);
    user-select: none;
    cursor: pointer;
  }
  .tags > .selected {
    background: var(--dark);
    color: var(--on-dark);
  }
</style>
