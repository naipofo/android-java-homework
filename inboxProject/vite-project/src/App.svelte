<script lang="ts">
  import Header from "./lib/Header.svelte";
  import ReportEdit from "./lib/pages/ReportEdit.svelte";
  import ReportList from "./lib/pages/ReportList.svelte";
  import ReportView from "./lib/pages/ReportView.svelte";
  import { ProjectService } from "./projectService";

  let projects = new ProjectService();
  function refresh() {
    projects.load().then(async (e) => {
      projectsData = await projects.getList();
    });
  }
  refresh()
  let projectsData = [];
  let loadedProject: any = {};
  let edited = null;

  let open: "list" | "view" | "edit" = "list";

  async function navigate(e: any) {
    loadedProject = await projects.getProject(e.detail.id);
    open = "view";
  }
  async function add() {
    edited = null;
    open = "edit";
  }
  function goHome() {
    edited = null;
    open = "list";
    new Promise(resolve => setTimeout(resolve, 200)).then(()=>refresh())
  }
  function editProject(e: any) {
    edited = e.detail.rowid;
    open = "edit";
  }
  function deleteProject(e: any) {
    projects.delete(e.detail.rowid)
    goHome()
  }
</script>

<Header />
<main>
  {#if open == "edit"}
    <ReportEdit id={edited} service={projects} on:home={goHome} />
  {:else if open == "view"}
    <ReportView
      projectData={loadedProject}
      on:back={goHome}
      on:edit={editProject}
      on:delete={deleteProject}
    />
  {:else}
    <ReportList projects={projectsData} on:navigate={navigate} on:add={add} />
  {/if}
</main>

<style>
  main {
    flex-grow: 1;
    display: flex;
  }
</style>
